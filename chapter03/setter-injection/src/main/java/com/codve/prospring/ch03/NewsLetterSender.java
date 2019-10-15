package com.codve.prospring.ch03;

public interface NewsLetterSender {
    void setSmtpServer(String smtpServer);

    String getSmtpServer();

    void setFromAddress(String address);

    String getFromAddress();

    void send();


}
