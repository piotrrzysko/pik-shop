package pl.elka.pw.pik.shop.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
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
public class SignUpControllerTest {
    @Autowired
    private SignUpController signUpController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(signUpController)
                .build();
    }

    @Test
    public void post_sign_up() throws Exception {
        String content = "{\"firstName\":\"User\",\"lastName\":\"Userski\",\"email\":\"user@pik.pl\",\"password\":\"IAmLegend\",\"passwordRepeat\":\"IAmLegend\"}";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/public/signup")
                        .contentType(MediaType.APPLICATION_JSON).content(content))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}
