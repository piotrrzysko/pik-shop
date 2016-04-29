package pl.elka.pw.pik.shop.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.elka.pw.pik.shop.PikShopBackendApplication;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PikShopBackendApplication.class)
@WebAppConfiguration
public class ProductControllerTest {
    @Autowired
    private ProductController productController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    public void should_return_400_if_null_fileds() throws Exception {
        MvcResult result = mockMvc.perform(post("/products").contentType(APPLICATION_JSON)
                .content("{\"name\":\"Product\",\"description\":\"Product descriptoin\"}"))
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void should_return_200() throws Exception {
        MvcResult result = mockMvc.perform(post("/products").contentType(APPLICATION_JSON)
                .content("{\"name\":\"Product\"," +
                        "\"description\":\"Product descriptoin\"," +
                        "\"price\":\"100.00\"," +
                        "\"availableCount\":\"1\"," +
                        "\"productState\":\"DELETED\"}"))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}
