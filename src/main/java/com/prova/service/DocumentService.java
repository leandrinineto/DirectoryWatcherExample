package com.prova.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.prova.enums.DataOriginEnum;
import com.prova.model.ClienteModel;
import com.prova.model.DetalheVendaModel;
import com.prova.model.VendaModel;
import com.prova.model.VendedorModel;

public class DocumentService {
	
	private String fileName;
	
	private List<VendedorModel> vendedorList;
	private List<ClienteModel> clienteList;
	private List<VendaModel> vendaList;
	
	public DocumentService(String fileName){
		this.fileName = fileName;
		vendedorList = new ArrayList<VendedorModel>();
		clienteList = new ArrayList<ClienteModel>();
		vendaList = new ArrayList<VendaModel>();
		
	}
	
	
	public void readFile() {
		
		Path path = Paths.get("C:\\data\\in\\" + fileName);
		BufferedReader reader = null;
		
			try {
				reader = Files.newBufferedReader(path);
			String input = null;
				while ((input = reader.readLine()) != null) {
				
				String type = input.substring(0, 3);
				DataOriginEnum inputType = DataOriginEnum.originValue(type);
				List<String> item = Arrays.asList(input.split("ç"));
				
				switch (inputType) {
					case VENDEDOR:
						VendedorModel vendedorModel = new VendedorModel(item.get(0), item.get(1), item.get(2), new BigDecimal(item.get(3)));
						vendedorList.add(vendedorModel);
						break;
					case CLIENTE:
						ClienteModel clienteModel = new ClienteModel(item.get(0), item.get(1), item.get(2), item.get(3));
						clienteList.add(clienteModel);
						break;
					case VENDA:
						String detalheVendaString = item.get(2);
						detalheVendaString = detalheVendaString.substring(detalheVendaString.indexOf("[") + 1);
						detalheVendaString = detalheVendaString.substring(0, detalheVendaString.indexOf("]"));
						List<String> detalheVendaStringArray = Arrays.asList(detalheVendaString.split("\\s*,\\s*"));
						
						List<DetalheVendaModel> listaDetalhes = new ArrayList<DetalheVendaModel>();
						//DETALHES DA VENDA
						for (String detalhes : detalheVendaStringArray) {
							List<String> venda = Arrays.asList(detalhes.split("\\s*-\\s*"));
							DetalheVendaModel model = new DetalheVendaModel(venda.get(0), new BigDecimal(venda.get(1)), new BigDecimal(venda.get(2)));
							listaDetalhes.add(model);
						}
						VendaModel vendaModel = new VendaModel(item.get(0), item.get(1), listaDetalhes, item.get(3));
						vendaList.add(vendaModel);
						break;
					default:
						
						break;
				}
}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		
	}
	
	
	public void writeFile() {
		
		HashMap<String, BigDecimal> vendedoresMap = new HashMap<String, BigDecimal>();

		BigDecimal biggestSale = new BigDecimal("0");
		String idVendaCara = null;
		//VENDA MAIS CARA
		for (VendaModel vendaModel : vendaList) {
			List<DetalheVendaModel> saleDetail = vendaModel.getSaleDetail();
			for (DetalheVendaModel detalhe : saleDetail) {
				BigDecimal resultPrice = detalhe.getPrice().multiply(detalhe.getQuantity());
				if (biggestSale.compareTo(resultPrice) == -1) {
					biggestSale = resultPrice;
					idVendaCara = vendaModel.getSaleId();
				}
				String salesmanName = vendaModel.getSalesmanName();
				
				//MAPA VALOR TOTAL VENDA VENDEDORES
				try {
					vendedoresMap.put(salesmanName,resultPrice.add(vendedoresMap.get(salesmanName)));
				}catch (Exception e) {
					vendedoresMap.put(salesmanName,resultPrice.add(BigDecimal.ZERO));
				}
			
			}
		}
		
		List<String> nomeVendedores = new ArrayList<String>(vendedoresMap.keySet());
		
		BigDecimal piorVeendedor = new BigDecimal("999999999999999");
		String nomePiorVendedor =  null;
		for (String nome : nomeVendedores) {
			BigDecimal bigDecimal = vendedoresMap.get(nome);
			if (bigDecimal.compareTo(piorVeendedor) == -1) {
				nomePiorVendedor = nome;
			}
		}
		
		int quantidadeCliente = clienteList.size();
		int quantidadeVendedores = vendedorList.size();
		
		try {
		      FileWriter myWriter = new FileWriter("C:\\data\\out\\out_" + fileName);
		      myWriter.write(quantidadeCliente+"-"+quantidadeVendedores+"-"+idVendaCara+"-"+nomePiorVendedor);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
		
	}

}
