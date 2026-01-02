/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Producers;

import Loggings.business.context.BusinessLogContext;
import Securities.RequestUserContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import java.util.UUID;

@RequestScoped
public class BusinessLogContextProducer {

    @Inject
    RequestUserContext requestUserContext;

    @Produces
    @RequestScoped
    public BusinessLogContext businessLogContext() {

        return BusinessLogContext.of(
                requestUserContext.userId(),
                requestUserContext.roles()
        );
    }
}
