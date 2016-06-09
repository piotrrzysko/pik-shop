package pl.elka.pw.pik.shop.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import pl.elka.pw.pik.shop.PikShopBackendApplication;

import static org.junit.Assert.assertEquals;

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
    public void add_should_return_400_if_null_fileds() throws Exception {
        MockMultipartFile productData = new MockMultipartFile("productData", "", "application/json",
                "{\"name\":\"Product\",\"description\":\"Product descriptoin\"}".getBytes());
        MockMultipartFile images = new MockMultipartFile("images", "", "text/plain", "".getBytes());

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.fileUpload("/public/products").file(productData).file(images))
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void add_should_return_200() throws Exception {
        MockMultipartFile productData = new MockMultipartFile("productData", "", "application/json",
                ("{\"name\":\"Product\"," +
                        "\"description\":\"Product descriptoin\"," +
                        "\"price\":\"100.00\"," +
                        "\"availableCount\":\"1\"," +
                        "\"productState\":\"DELETED\"}").getBytes());
        MockMultipartFile images = new MockMultipartFile("images", "", "text/plain", "".getBytes());

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.fileUpload("/public/products").file(productData).file(images))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Transactional
    public void get_list_200() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/public/products/")).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Transactional
    public void get_product_by_id_200() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/public/products/{id}", 1l)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Transactional
    public void delete_product_by_id_200() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/public/products/{id}", 1l)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}
