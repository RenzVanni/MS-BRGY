package com.ms_spring_brgy.user.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "resident", url = "${service.url.resident}")
public interface Resident_Client {

}
