package com.wimy.android.sendme;

public class SendMeData
{
    private String subject;
    private String body;

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public void copyFrom(SendMeData filtered)
    {
        setSubject(filtered.getSubject());
        setBody(filtered.getBody());
    }
}
