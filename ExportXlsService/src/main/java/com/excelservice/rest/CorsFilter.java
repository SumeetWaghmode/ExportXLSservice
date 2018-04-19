package com.excelservice.rest;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Provider
public class CorsFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest req, ContainerResponse cresp) {
		cresp.getHttpHeaders().putSingle("access-control-allow-origin", "*");
        cresp.getHttpHeaders().putSingle("access-control-allow-methods", "GET, POST, OPTIONS");
        //cresp.getHttpHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

		return cresp;
	}

}
