package es.florida.ae5;

import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.net.httpserver.HttpExchange;

public class GestorHTTP implements HttpHandler {

	float temperaturaActual = 15.00f;
	float temperaturaTermostato = 15.00f;

	// Metodo: handle
	// Descripcion: metodo que gestiona las peticiones http
	// Parametros de entrada: HttpExchange
	// Parametros de salida: no.
	// Informacion Adicional:
	// - He hecho que funcione con multiple POST, es decir, usando RAW en Postman
	// puedes incluir
	// dos lineas a la vez, por ejemplo:
	// setTemperatura=20
	// notificarAveria:email_remitente=ximopruebas506@gmail.com;pass_remitente=javamolamogollon
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String requestParamValue = null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange, requestParamValue);
		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange, requestParamValue);
		}
	}

	// Metodo: handleGetRequest
	// Descripcion: metodo que coge la URL y la parte para obtener el parametro GET
	// Parametros de entrada: HttpExchange
	// Parametros de salida: no.
	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1];
	}

	// Metodo: handleGETResponse
	// Descripcion: Metodo que gestiona el GET 
	// Parametros de entrada: HttpExchange, String
	// Parametros de salida: no.
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "<html><body>ERROR GET</body></html>";
		if (requestParamValue.equals("temperaturaActual")) {
			htmlResponse = "<html><body>" + "<p> Temperatura Actual: " + temperaturaActual + "</p>"
					+ "<p> Temperatura Termostato: " + temperaturaTermostato + "</p>" + "</body></html>";
		}
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
	}

	// Metodo: handlePostRequest
	// Descripcion: Metodo que lee el body del post
	// Parametros de entrada: HttpExchange
	// Parametros de salida: no.
	private String handlePostRequest(HttpExchange httpExchange) {
		InputStream inputStream = httpExchange.getRequestBody();
		// Procesar lo que hay en inputStream, por ejemplo linea a linea y guardarlo
		// todo
		// en un string, que sera el que devuelve el metodo
		String postRequest = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String linea = br.readLine();
			while (linea != null) {
				postRequest += linea + "&";
				linea = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postRequest;
	}

	// Metodo: handlePOSTResponse
	// Descripcion: Metodo que gestiona el GET 
	// Parametros de entrada: HttpExchange httpExchange, String requestParamValue
	// Parametros de salida: no.
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "ERROR POST";
		String[] respuestasPost = requestParamValue.split("&");
		for (String s : respuestasPost) {

			if (s.split(":")[0].equals("notificarAveria")) {
				String[] infoCorreo = s.split(":")[1].split(";");
				String email_remitente = infoCorreo[0].split("=")[1];
				String pass_remitente = infoCorreo[1].split("=")[1];
				System.out.println(email_remitente);
				System.out.println(pass_remitente);
				String mensaje="Averia Ximo";
				String urlImagen="florida.jpg";
				String urlPdf="dummy.pdf";
				String[] emailDestino= {"mantenimientoinvernalia@gmail.com", "megustaelfresquito@gmail.com"};
				String[] anexo= {urlImagen, urlPdf};
				try {
					envioMail(mensaje,email_remitente,pass_remitente,emailDestino,anexo);
				} catch (UnsupportedEncodingException e) {
					
					e.printStackTrace();
				} catch (MessagingException e) {
					
					e.printStackTrace();
				}

				htmlResponse = "Informacion de Correo Recibida";
				httpExchange.sendResponseHeaders(200, htmlResponse.length());
				outputStream.write(htmlResponse.getBytes());
				outputStream.flush();
				outputStream.close();
			}

			if (s.split("=")[0].equals("setTemperatura")) {
				String[] opcion = s.split("=");
				htmlResponse = "Temperatura del Termostato establecida a: " + opcion[1];
				temperaturaTermostato = Integer.parseInt(opcion[1]);
				httpExchange.sendResponseHeaders(200, htmlResponse.length());
				outputStream.write(htmlResponse.getBytes());
				outputStream.flush();
				outputStream.close();
				regularTemperatura();
			}
		}
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
	}

	// Metodo: regularTemperatura
	// Descripcion: Metodo que regula la temperatura actual a la del termostato
	// Parametros de entrada: no.
	// Parametros de salida: no.
	private void regularTemperatura() {
		while (temperaturaActual != temperaturaTermostato) {
			if (temperaturaActual > temperaturaTermostato) {
				temperaturaActual--;
			} else {
				temperaturaActual++;
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	// Metodo: envioMail
	// Descripcion: Metodo que envia un email al tecnico
	// Parametros de entrada: String mensaje, String email_remitente, String email_remitente_pass, String[] email_destino, String[] anexo
	// Parametros de salida: no.
	private void envioMail(String mensaje, String email_remitente, String email_remitente_pass, String[] email_destino,
			String[] anexo) throws UnsupportedEncodingException, MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email_remitente));
		message.addRecipients(Message.RecipientType.TO, email_destino[0]);
		message.setSubject("AVERIA");
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		BodyPart messageBodyPart2 = new MimeBodyPart();
		DataSource src = new FileDataSource(anexo[0]);
		messageBodyPart2.setDataHandler(new DataHandler(src));
		messageBodyPart2.setFileName(anexo[0]);
		BodyPart messageBodyPart3 = new MimeBodyPart();
		DataSource src2 = new FileDataSource(anexo[1]);
		messageBodyPart3.setDataHandler(new DataHandler(src2));
		messageBodyPart3.setFileName(anexo[1]);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		multipart.addBodyPart(messageBodyPart3);
		message.setContent(multipart);
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
