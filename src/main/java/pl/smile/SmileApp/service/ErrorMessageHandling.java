package pl.smile.SmileApp.service;

import org.springframework.validation.BindingResult;

public interface ErrorMessageHandling {
    void setErrorMessageToView(BindingResult result);
}
