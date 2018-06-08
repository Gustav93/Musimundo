package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.ProductReport;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedType;

import java.io.File;

public interface MailService {

    void sendMail(ProductReport productReport, File attachment, Company company);
}
