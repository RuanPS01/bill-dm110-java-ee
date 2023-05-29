package br.inatel.billproject.api;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/bill")
public interface BillService {
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String createBill(BillTO bill);

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String updateBill(@PathParam("id") String id, BillTO bill);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String listBills(@QueryParam("searchText") String searchText,
                     @QueryParam("minExpirationDate") String minExpirationDate,
                     @QueryParam("maxExpirationDate") String maxExpirationDate,
                     @QueryParam("minPaidDate") String minPaidDate,
                     @QueryParam("maxPaidDate") String maxPaidDate);

    @PUT
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    String deleteBill(@PathParam("id") String id);
}

