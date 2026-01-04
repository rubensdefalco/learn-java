package service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import dao.VendaDAO;
import model.Venda;

public class RelatorioVendas {

	
	//MÉTODO QUE USA DAO + PDF 
	public static void gerar(File caminho) {

		try {
			PdfWriter writer = new PdfWriter(caminho);
			PdfDocument pdf = new PdfDocument(writer);
			Document doc = new Document(pdf);

			// Fonte em negrito
			PdfFont fonteNegrito = PdfFontFactory.createFont("Helvetica-Bold", "WinAnsi"

			);

			Paragraph titulo = new Paragraph("Relatório de Vendas").setFont(fonteNegrito).setFontSize(18);

			doc.add(titulo);
			doc.add(new Paragraph(" "));
			// 5 colunas
			Table tabela = new Table(5);
			tabela.addCell("ID");
			tabela.addCell("Cliente");
			tabela.addCell("Produto");
			tabela.addCell("Quantidade");
			tabela.addCell("Total");

			List<Venda> vendas = new VendaDAO().listarTodos();

			for (Venda v : vendas) {
				tabela.addCell(String.valueOf(v.getId()));
				tabela.addCell(v.getCliente().getNome());
				tabela.addCell(v.getProduto().getNome());
				tabela.addCell(String.valueOf(v.getQuantidade()));
				tabela.addCell(String.format("R$ %.2f", v.getTotal()));
			}

			doc.add(tabela);
			doc.close();

			System.out.println("PDF gerado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
