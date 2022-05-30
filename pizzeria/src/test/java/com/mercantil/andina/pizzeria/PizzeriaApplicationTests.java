package com.mercantil.andina.pizzeria;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.snippet.Attributes.Attribute;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import  org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercantil.andina.pizzeria.api.rest.domain.IFDetalle;
import com.mercantil.andina.pizzeria.api.rest.domain.IFDetalleTest;
import com.mercantil.andina.pizzeria.api.rest.domain.IFPedido;
import com.mercantil.andina.pizzeria.api.rest.domain.IFPedidoTest;
import com.mercantil.andina.pizzeria.api.rest.domain.IFProducto;
import com.mercantil.andina.pizzeria.backend.entity.Producto;
import com.mercantil.andina.pizzeria.service.CalculoService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
public class PizzeriaApplicationTests 
{
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	CalculoService calculoService;
    
    @Test
    public void welcome() throws Exception 
    {
        mockMvc.perform(get("/pizzeria/api/public/welcome"))
            .andExpect(status().isOk())
            .andDo(document("welcome"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void testAuthenticate() throws Exception 
    {
    //	this.mockMvcAuthorize = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    	
    	MultiValueMap <String, String> args = new LinkedMultiValueMap <> ();
    	args.add("username", "admin");
    	args.add("password", "admin");
    	    	
    	 ResultActions result= mockMvc.perform(post("/pizzeria/api/authenticate").params(args))
            .andExpect(status().is(401))
            .andDo(document("authenticate",requestParameters(
                    parameterWithName("username").description("Nombre de Usuario"),
                    parameterWithName("password").description("Contraseña de Usuario")
                    )));
    	 
    	 String resultString = result.andReturn().getResponse().getContentAsString();
    	 
    	 System.out.println("TOKEN --------------------- "+ resultString);
    	 System.out.println("TOKEN 2 --------------------- "+ result.toString());
    }
    
    private RequestHeadersSnippet headerDoc()
    {
    	Attribute att=new Attribute("Authorization", "Bearer JWT");		
		return requestHeaders(headerWithName("Authorization").attributes(att).description("Bearer {JWT}"));
    }
    
//    @SuppressWarnings("unchecked")
//	@WithMockUser(value = "spring")
//    @Test
//    public void topsecret() throws Exception
//    {
//    	IFPayload sat1=new IFPayload();
//    	sat1.setName("Kenobi");
//    	sat1.setDistance(100.0);
//    	sat1.setMessage(new String[] {"este","","","mensaje",""});
//    	
//    	IFPayload sat2=new IFPayload();
//    	sat2.setName("Skywalker");
//    	sat2.setDistance(115.5);
//    	sat2.setMessage(new String[] {"","es","","","secreto"});
//    	
//    	IFPayload sat3=new IFPayload();
//    	sat3.setName("Sato");
//    	sat3.setDistance(142.7);
//    	sat3.setMessage(new String[] {"este","","un","",""});
//    	
//    	
//    	List<IFPayload> payloads=new ArrayList<IFPayload>();
//    	payloads.add(sat1);
//    	payloads.add(sat2);
//    	payloads.add(sat3);
//    	
//    	ObjectMapper mapper = new ObjectMapper();
//    	mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//    	String jsonInString = mapper.writerWithView(OperacionFuegoQuasarJsonView.ViewOperacionFuegoQuasar.class).writeValueAsString(payloads);
//    	
//    	IFPosition iFPosition = new IFPosition();
//    	iFPosition.setX(-100.0);
//    	iFPosition.setY(75.5);
//    	
//    	IFNave iFNave = new IFNave();
//    	iFNave.setPosition(iFPosition);
//    	iFNave.setMessage("este es un mensaje secreto");
//  
//    	when(calculoService.getIFNaveByPayload(any(ArrayList.class))).thenReturn(iFNave);
//    	
//    	mockMvc.perform(post("/pizzeria/api/private/topsecret")
//    			  .accept(MediaType.APPLICATION_JSON)
//    	          .contentType(MediaType.APPLICATION_JSON)
//    	          .content(jsonInString)
//    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
//    	          .andExpect(status().isOk())
//        .andDo(document("topsecret",arryTopSecret(),headerDoc()));
//    }
//    
//    private RequestFieldsSnippet arryTopSecret() 
//    {
//        return requestFields(
//        		fieldWithPath("[].name").description("Nombre del Satélite que recibe el Mensaje").attributes(key("constraints").value("Not Null")),
//        		fieldWithPath("[].distance").description("Distancia entre la Nave y el Satélite").attributes(key("constraints").value("Not Null. Valor numérico")),
//        		fieldWithPath("[].message[]").description("Mensaje").attributes(key("constraints").value("Not Null. No blanco o vacío"))        		
//		);
//    }    
//   
//	@WithMockUser(value = "spring")
//    @Test
//    public void topsecret_split() throws Exception
//    {
//    	IFPayload sat1=new IFPayload();
//    	sat1.setName("Kenobi");
//    	sat1.setDistance(100.0);
//    	sat1.setMessage(new String[] {"este","","","mensaje",""});        	
//    	
//    	ObjectMapper mapper = new ObjectMapper();
//    	mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//    	String jsonInString = mapper.writerWithView(OperacionFuegoQuasarJsonView.ViewOperacionFuegoQuasar.class).writeValueAsString(sat1);    	
//  
//    	when(ultimoDatoSateliteService.guardarInformacion(any(IFPayload.class),any(String.class))).thenReturn("");
//    	
//    	mockMvc.perform(post("/pizzeria/api/private/topsecret_split/{satelliteName}","Kenobi")
//    			  .accept(MediaType.APPLICATION_JSON)
//    	          .contentType(MediaType.APPLICATION_JSON)
//    	          .content(jsonInString)
//    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
//    	          .andExpect(status().isOk())
//        .andDo(document("topsecret_split",topSecretSplit(),pathParameters(
//                parameterWithName("satelliteName").description("Nombre del Satélite")
//                ),headerDoc()));   	
//    	
//    }
//    
//    private RequestFieldsSnippet topSecretSplit() 
//    {
//    	 return requestFields(
//         		
//         		fieldWithPath("name").description("Nombre del Satélite que recibe el Mensaje").attributes(key("constraints").value("Not Null")),
//        		fieldWithPath("distance").description("Distancia entre la Nave y el Satélite").attributes(key("constraints").value("Not Null. Valor numérico")),
//        		fieldWithPath("message[]").description("Mensaje").attributes(key("constraints").value("Not Null. No blanco o vacío"))     
//         );
//    }
//    
//    @WithMockUser(value = "spring")
//    @Test
//    public void topsecret_split_get() throws Exception
//    {
//    	IFPayload sat1=new IFPayload();
//    	sat1.setName("Kenobi");
//    	sat1.setDistance(100.0);
//    	sat1.setMessage(new String[] {"este","","","mensaje",""});   
//    	
//    	IFPosition iFPosition = new IFPosition();
//    	iFPosition.setX(-100.0);
//    	iFPosition.setY(75.5);
//    	
//    	IFNave iFNave = new IFNave();
//    	iFNave.setPosition(iFPosition);
//    	iFNave.setMessage("este es un mensaje secreto");
//    	
//    	ObjectMapper mapper = new ObjectMapper();
//    	mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//    	String jsonInString = mapper.writerWithView(OperacionFuegoQuasarJsonView.ViewOperacionFuegoQuasar.class).writeValueAsString(sat1);    	
//  
//    	when(ultimoDatoSateliteService.guardarInformacionGetPosicion(any(IFPayload.class),any(String.class))).thenReturn(iFNave);
//    	
//    	mockMvc.perform(post("/pizzeria/api/private/topsecret_split/{satelliteName}","Kenobi")
//    			  .accept(MediaType.APPLICATION_JSON)
//    	          .contentType(MediaType.APPLICATION_JSON)
//    	          .content(jsonInString)
//    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
//    	          .andExpect(status().isOk())
//        .andDo(document("topsecret_split_get",topSecretSplitGet(),pathParameters(
//                parameterWithName("satelliteName").description("Nombre del Satélite")
//                ),headerDoc()));   	
//    	
//    }
//    
//    private RequestFieldsSnippet topSecretSplitGet() 
//    {
//    	 return requestFields(
//         		
//         		fieldWithPath("name").description("Nombre del Satélite que recibe el Mensaje").attributes(key("constraints").value("Not Null")),
//        		fieldWithPath("distance").description("Distancia entre la Nave y el Satélite").attributes(key("constraints").value("Not Null. Valor numérico")),
//        		fieldWithPath("message[]").description("Mensaje").attributes(key("constraints").value("Not Null. No blanco o vacío"))     
//         );
//    }
    
/********** PRODUCTOS **********/
    
    @WithMockUser(value = "spring")
    @Test
    public void crearProducto() throws Exception
    {
    	IFProducto producto=new IFProducto();    	
    	producto.setId("89efb206-2aa6-4e21-8a23-5765e3de1f31");
    	producto.setNombre("Jamón y morrones");
    	producto.setDescripcionCorta("Pizza de jamón y morrones");
    	producto.setDescripcionLarga("Mozzarella, jamón, morrones y aceitunas verdes");
    	producto.setPrecioUnitario(550.00);
    	
    	
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(producto); 
    	
    	System.out.println(jsonInString);
    	Producto prod=new Producto();
       	
    	when(calculoService.save(any(IFProducto.class),anyBoolean(),any(String.class))).thenReturn(prod);
    	
    	mockMvc.perform(post("/pizzeria/api/private/productos")
    			  .accept(MediaType.APPLICATION_JSON)
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .content(jsonInString)
    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
    	          .andExpect(status().is(201))
        .andDo(document("crearProducto",crearProductoImput(),headerDoc()));
    }
    
    private RequestFieldsSnippet crearProductoImput() 
    {    	
        return requestFields(        	 
            fieldWithPath("id").description("Id de Producto").attributes(key("constraints").value("Not Null")),
            fieldWithPath("nombre").description("Nombre del Producto").attributes(key("constraints").value("Not Null")),
            fieldWithPath("descripcionCorta").description("Descripción Corta del Producto").attributes(key("constraints").value("Nullable")),  
            fieldWithPath("descripcionLarga").description("Descripción Larga del Producto").attributes(key("constraints").value("Nullable")),
        	fieldWithPath("precioUnitario").description("Precio Unitario del Producto").attributes(key("constraints").value("Nullable"))
        );
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void borrarProducto() throws Exception
    {   	
    	
    	mockMvc.perform(delete("/pizzeria/api/private/productos/{idProducto}","89efb206-2aa6-4e21-8a23-5765e3de1f31")  	         
  	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
  	          .andExpect(status().is(204))
  	        .andDo(document("borrarProducto", pathParameters(
                    parameterWithName("idProducto").description("ID de Producto")
                    ),headerDoc()));
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void modificarProducto() throws Exception
    {
    	IFProducto producto=new IFProducto(); 
    	producto.setId("89efb206-2aa6-4e21-8a23-5765e3de1f31");
    	producto.setNombre("Jamón");
    	producto.setDescripcionCorta("Pizza de jamón");
    	producto.setDescripcionLarga("Mozzarella, jamón, y aceitunas verdes");
    	producto.setPrecioUnitario(550.00);
    	
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(producto); 
    	
    	System.out.println(jsonInString);
    	Producto prod=new Producto();
       	
    	when(calculoService.save(any(IFProducto.class),anyBoolean(),any(String.class))).thenReturn(prod);
    	
    	mockMvc.perform(put("/pizzeria/api/private/productos/{idProducto}","89efb206-2aa6-4e21-8a23-5765e3de1f31")
    			  .accept(MediaType.APPLICATION_JSON)
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .content(jsonInString)
    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
    	          .andExpect(status().is(204))
        .andDo(document("modificarProducto",modificarProductoImput(),pathParameters(
                    parameterWithName("idProducto").description("ID de Producto")
                    ),headerDoc()));
    }
    
    private RequestFieldsSnippet modificarProductoImput() 
    {    	
        return requestFields(
        		fieldWithPath("id").description("Id de Producto").attributes(key("constraints").value("Not Null")),
        		fieldWithPath("nombre").description("Nombre del Producto").attributes(key("constraints").value("Not Null")),
                fieldWithPath("descripcionCorta").description("Descripción Corta del Producto").attributes(key("constraints").value("Nullable")),  
                fieldWithPath("descripcionLarga").description("Descripción Larga del Producto").attributes(key("constraints").value("Nullable")),
            	fieldWithPath("precioUnitario").description("Precio Unitario del Producto").attributes(key("constraints").value("Nullable"))   
        );
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void consultarProducto() throws Exception
    {
    	IFProducto producto=new IFProducto();     	
    	producto.setNombre("Jamón y morrones");
    	producto.setDescripcionCorta("Pizza de jamón y morrones");
    	producto.setDescripcionLarga("Mozzarella, jamón, morrones y aceitunas verdes");
    	producto.setPrecioUnitario(550.00);
    	
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(producto); 
    	
    	System.out.println(jsonInString);    	
       	
    	when(calculoService.getProducto(any(String.class))).thenReturn(producto);
    	
    	mockMvc.perform(get("/pizzeria/api/private/productos/{idProducto}","89efb206-2aa6-4e21-8a23-5765e3de1f31")
    			  .accept(MediaType.APPLICATION_JSON)
    	          .contentType(MediaType.APPLICATION_JSON)    	          
    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
    	          .andExpect(status().isOk())
        .andDo(document("consultarProducto",pathParameters(
                    parameterWithName("idProducto").description("ID de Producto")
                    ),headerDoc()));
    }   
    
/********** PEDIDOS **********/
    
    @WithMockUser(value = "spring")
    @Test
    public void crearPedido() throws Exception
    {
    	IFPedidoTest pedido=new IFPedidoTest();    	
    	pedido.setDireccion("Dorton Road 80");
    	pedido.setEmail("tsayb@opera.com");
    	pedido.setTelefono("(0351) 48158101");
    	pedido.setHorario("21:00");    	
    	
    	IFDetalleTest detalle1 = new IFDetalleTest();
    	detalle1.setProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
    	detalle1.setCantidad(1);
    	
    	IFDetalleTest detalle2 = new IFDetalleTest();
    	detalle2.setProducto("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
    	detalle2.setCantidad(1);
    	
    	List<IFDetalleTest> lstDetalles = new ArrayList<>();
    	lstDetalles.add(detalle1);
    	lstDetalles.add(detalle2);
    	
    	pedido.setDetalle(lstDetalles);    	
    	
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(pedido); 
    	
    	System.out.println(jsonInString);
    	IFPedido respuesta = new IFPedido();
    	respuesta.setFecha(LocalDate.parse("2020-05-24"));
    	respuesta.setDireccion("Dorton Road 80");
    	respuesta.setEmail("tsayb@opera.com");
    	respuesta.setTelefono("(0351) 48158101");
    	respuesta.setHorario("21:00");
    	
    	IFDetalle detalle1rta = new IFDetalle();
    	detalle1rta.setProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
    	detalle1rta.setNombre("Jamón y morrones");
    	detalle1rta.setCantidad(1);
    	detalle1rta.setImporte(550.00);
    	
    	IFDetalle detalle2rta = new IFDetalle();
    	detalle2rta.setProducto("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
    	detalle2rta.setNombre("Palmitos");
    	detalle2rta.setCantidad(1);
    	detalle2rta.setImporte(600.00);
    	
    	List<IFDetalle> lstDetallesRta = new ArrayList<>();
    	lstDetallesRta.add(detalle1rta);
    	lstDetallesRta.add(detalle2rta);
    	
    	respuesta.setDetalle(lstDetallesRta);
	
    	respuesta.setTotal(1150.00);
    	respuesta.setDescuento(false);
    	respuesta.setEstado("PENDIENTE");
       	
    	when(calculoService.save(any(IFPedido.class))).thenReturn(respuesta);
    	
    	mockMvc.perform(post("/pizzeria/api/private/pedidos")
    			  .accept(MediaType.APPLICATION_JSON)
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .content(jsonInString)
    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
    	          .andExpect(status().is(201))
        .andDo(document("crearPedido",crearPedidoImput(),headerDoc()));
    }
    
    private RequestFieldsSnippet crearPedidoImput() 
    {    	
        return requestFields(        	 
            fieldWithPath("direccion").description("Dirección de entrega de Pedido").attributes(key("constraints").value("Not Null")),
            fieldWithPath("email").description("Email del cliente").attributes(key("constraints").value("Nullable")),
            fieldWithPath("telefono").description("Teléfono del cliente").attributes(key("constraints").value("Not Null")),  
            fieldWithPath("horario").description("Horario de entrega del Pedido").attributes(key("constraints").value("Not Null")),
        	fieldWithPath("detalle[].producto").description("Id del Producto").attributes(key("constraints").value("Not Null")),
        	fieldWithPath("detalle[].cantidad").description("Cantidad de Productos").attributes(key("constraints").value("Not Null"))
        );
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void consultarPedidos() throws Exception
    {
    	List<IFPedido> lstPedidos = new ArrayList<>();
    	IFPedido pedido1 = new IFPedido();
    	pedido1.setFecha(LocalDate.parse("2020-05-24"));
    	pedido1.setDireccion("Dorton Road 80");
    	pedido1.setEmail("tsayb@opera.com");
    	pedido1.setTelefono("(0351) 48158101");
    	pedido1.setHorario("21:00");
    	
    	IFDetalle detalle1rta = new IFDetalle();
    	detalle1rta.setProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
    	detalle1rta.setNombre("Jamón y morrones");
    	detalle1rta.setCantidad(1);
    	detalle1rta.setImporte(550.00);
    	
    	IFDetalle detalle2rta = new IFDetalle();
    	detalle2rta.setProducto("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
    	detalle2rta.setNombre("Palmitos");
    	detalle2rta.setCantidad(1);
    	detalle2rta.setImporte(600.00);
    	
    	List<IFDetalle> lstDetallesRta = new ArrayList<>();
    	lstDetallesRta.add(detalle1rta);
    	lstDetallesRta.add(detalle2rta);
    	
    	pedido1.setDetalle(lstDetallesRta);
	
    	pedido1.setTotal(1150.00);
    	pedido1.setDescuento(false);
    	pedido1.setEstado("PENDIENTE");
    	
    	lstPedidos.add(pedido1);
    	
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(lstPedidos); 
    	
    	System.out.println(jsonInString);    	
       	
    	when(calculoService.getPedidos(any(LocalDate.class))).thenReturn(lstPedidos);
    	
    	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    	requestParams.add("fecha", "2020-05-24");
    	
    	mockMvc.perform(get("/pizzeria/api/private/pedidos").params(requestParams)
    			  .accept(MediaType.APPLICATION_JSON)
    	          .contentType(MediaType.APPLICATION_JSON)    	          
    	          .header("Authorization", "Bearer Drhq4w.p329uGy3TwdCM9.iTadqV3aEG"))
    	          .andExpect(status().isOk())
        .andDo(document("consultarPedidos",headerDoc()));
    }   
}
