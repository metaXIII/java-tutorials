package com.metaxiii.fr.model;

import jakarta.activation.FileDataSource;
import jakarta.mail.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.simplejavamail.MailException;
import org.simplejavamail.api.email.AttachmentResource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.Recipient;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

@Slf4j
@SuppressWarnings("unused")
public class SimpleMail {

  private static final String RECIPIENT_EXAMPLE_COM = "recipient@example.com";
  private static final String SENDER_EXAMPLE_COM = "sender@example.com";

  private SimpleMail() throws IllegalAccessException {
    throw new IllegalAccessException("You should not create this class");
  }

  public static void forwardingEmail(final Email receivedEmail) {
    final Email email = EmailBuilder
      .forwarding(receivedEmail)
      .from(SENDER_EXAMPLE_COM)
      .prependText("This is an Forward Email. See below email:")
      .buildEmail();
  }

  public static void handleExceptionWhenSendingEmail() {
    try {
      sendPlainTextEmail();
      log.info("Email sent successfully!");
    } catch (final MailException e) {
      log.error("Error: {}", e.getMessage());
    }
  }

  public static void sendPlainTextEmail() {
    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to(RECIPIENT_EXAMPLE_COM)
      .withSubject("Email with Plain Text!")
      .withPlainText("This is a test email sent using SJM.")
      .buildEmail();
    sendEmail(email);
  }

  private static void sendEmail(final Email email) {
    try (
      final Mailer mailer = MailerBuilder
        .withSMTPServer("smtp.example.com", 25, "username", "password")
        .withMaximumEmailSize(1024 * 1024 * 5) // 5 Megabytes
        .buildMailer()
    ) {
      final boolean validate = mailer.validate(email);
      if (validate) {
        mailer.sendMail(email);
      } else {
        log.info("Invalid email address.");
      }
    } catch (final Exception e) {
      log.error(e.getMessage());
    }
  }

  public static void replyingToEmail(final Email receivedEmail) {
    EmailBuilder
      .replyingTo(receivedEmail)
      .from(SENDER_EXAMPLE_COM)
      .prependText("This is a Reply Email. Original email included below:")
      .buildEmail();
  }

  public static void sendEmailWithAttachment() {
    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to(RECIPIENT_EXAMPLE_COM)
      .withSubject("Email with Plain Text and Attachment!")
      .withPlainText("This is a test email with attachment sent using SJM.")
      .withAttachment("important_document.pdf", new FileDataSource("path/to/important_document.pdf"))
      .buildEmail();
    sendEmail(email);
  }

  private static void sendEmailWithDeliveryReadRecipient() {
    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to(RECIPIENT_EXAMPLE_COM)
      .withSubject("Email with Delivery/Read Receipt Configured!")
      .withPlainText("This is an email sending with delivery/read receipt.")
      .withDispositionNotificationTo(new Recipient("name", "address@domain.com", Message.RecipientType.TO))
      .withReturnReceiptTo(new Recipient("name", "address@domain.com", Message.RecipientType.TO))
      .buildEmail();

    sendEmail(email);
  }

  public static void sendEmailWithMultipleAttachment() {
    final List<AttachmentResource> arList = new ArrayList<>();
    arList.add(new AttachmentResource("important_document.pdf", new FileDataSource("path/to/important_document.pdf")));
    arList.add(new AttachmentResource("company_logo.png", new FileDataSource("path/to/company_logo.png")));
    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to(RECIPIENT_EXAMPLE_COM)
      .withSubject("Email with Plain Text and multiple Attachments!")
      .withPlainText("This is a test email with attachment sent using SJM.")
      .withAttachments(arList)
      .buildEmail();
    sendEmail(email);
  }

  public static void sendHTMLTextWithEmbeddedImageEmail() {
    final String htmlContent =
      "<h1>This is an email with HTML content</h1>" +
      "<p>This email body contains additional information and formatting.</p>" +
      "<img src=\"cid:company_logo\" alt=\"Company Logo\">";

    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to(RECIPIENT_EXAMPLE_COM)
      .withSubject("Email with HTML and Embedded Image!")
      .withHTMLText(htmlContent)
      .withEmbeddedImage("company_logo", new FileDataSource("path/to/company_logo.png"))
      .buildEmail();
    sendEmail(email);
  }

  public static void sendPlainTextEmailToMultipleRecipient() {
    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to("recipient1@example.com, recipient2@example.com, recipient3@example.com")
      .withSubject("Email with Plain Text!")
      .withPlainText("This is a test email sent using SJM to multiple recipients.")
      .buildEmail();
    sendEmail(email);
  }

  public static void setCustomHeaderWhenSendingEmail() {
    final Email email = EmailBuilder
      .startingBlank()
      .from(SENDER_EXAMPLE_COM)
      .to(RECIPIENT_EXAMPLE_COM)
      .withSubject("Email with Custom Header")
      .withPlainText("This is an important message.")
      .withHeader("X-Priority", "1")
      .buildEmail();
    sendEmail(email);
  }
}
