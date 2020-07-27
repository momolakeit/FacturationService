package com.momoProjet.FacturationService.controllers;

import com.momoProjet.FacturationService.FacturationApplication;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = FacturationApplication.class)
@ActiveProfiles("test")
class FacturationApplicationControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacturationApplicationController facturationApplicationController;

    @Test
    public void createFactureReturnFacture() throws Exception {
        MockMvc mvc = initMockMvc();
        LinkedMultiValueMap <String,String> requestParams = new LinkedMultiValueMap<>();
        JSONObject sendObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject proprioFacture1 =new JSONObject();
        proprioFacture1.put("email","proprio1@gmail.com");
        jsonArray.put(proprioFacture1);
        JSONObject proprioFacture2 =new JSONObject();
        proprioFacture2.put("email","proprio2@gmail.com");
        jsonArray.put(proprioFacture2);
        sendObj.put("montant","100");
        sendObj.put("proprioList",jsonArray.toString());
        MvcResult result= mvc.perform(MockMvcRequestBuilders.post(   "/creerFacture").
                                                            content(sendObj.toString()).
                                                            contentType(MediaType.APPLICATION_JSON).
                                                            accept(MediaType.APPLICATION_JSON)).
                                                            andExpect(status().isOk()).
                                                            andReturn();

        JSONObject valeurRetour =new JSONObject(result.getResponse().getContentAsString());
        assertEquals(100.0,valeurRetour.get("montant"));
    }
    @Test
    public void findFactureById() throws Exception {
        MockMvc mvc = initMockMvc();

        MvcResult result =mvc.perform(MockMvcRequestBuilders.get("/findFactureById").content("5").
                                                contentType(MediaType.APPLICATION_JSON).
                                                accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        String s =result.getResponse().getContentAsString();
        JSONObject returnVal = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(100.0,returnVal.get("montant"));
    }
    @Test
    public void updateFactureById() throws Exception {
        MockMvc mockMvc = initMockMvc();
        JSONObject factureModifié =new JSONObject();
        factureModifié.put("id","5");
        factureModifié.put("montant","99999");
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.put(   "/modifierFacture").
                                                                content(factureModifié.toString()).
                                                                contentType(MediaType.APPLICATION_JSON).
                                                                accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                                                                    andReturn();
        JSONObject returnValue = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(99999.00,returnValue.get("montant"));
    }
    @Test
    public void creerFactureCompagnie() throws Exception {
        MockMvc mockMvc = initMockMvc();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","proprio1@gmail.com");
        MvcResult result ;
                mockMvc.perform(MockMvcRequestBuilders.post(  "/creerFacture_Compagnie").
                                                                content(jsonObject.toString()).
                                                                contentType(MediaType.APPLICATION_JSON).
                                                                accept(MediaType.APPLICATION_JSON)).
                                                                andExpect(status().isOk()).
                                                                andReturn();

        /*JSONObject returnValue = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(130.0,returnValue.get("revenu_brut"));
        assertEquals(78.0,returnValue.get("revenu_net"));
        assertEquals(52.0,returnValue.get("paie_employes"));*/


    }
    private MockMvc initMockMvc(){
        return MockMvcBuilders.standaloneSetup(facturationApplicationController).build();
    }
}