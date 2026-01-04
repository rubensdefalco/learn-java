package service;

import java.io.File;
//View -> chama Controller
//Controller -> chama Service
//Service -> chama DAO
public class RelatorioController {

	private RelatorioVendas service = new RelatorioVendas();

	public void gerarRelatorio(File arquivo) throws Exception {
		service.gerar(arquivo);
	}
}
