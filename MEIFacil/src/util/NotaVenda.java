package util;

import com.itextpdf.kernel.font.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;

import model.Venda;

public class NotaVenda {

	public static void gerar(Venda venda, String caminho) {
		try {
			PdfWriter w = new PdfWriter(caminho);
			PdfDocument pdf = new PdfDocument(w);
			Document doc = new Document(pdf);

			PdfFont negrito = PdfFontFactory.createFont("Helvetica-Bold", "WinAnsi");

			doc.add(new Paragraph("NOTA DE VENDA").setFont(negrito).setFontSize(16));

			doc.add(new Paragraph("MEI Fácil - Sistema de Gestão"));
			doc.add(new Paragraph("CNPJ: 00.000.000/0001-00"));
			doc.add(new Paragraph("------------------------------------------------"));

			doc.add(new Paragraph("Cliente: " + venda.getCliente().getNome()));
			doc.add(new Paragraph("Produto: " + venda.getProduto().getNome()));
			doc.add(new Paragraph("Quantidade: " + venda.getQuantidade()));
			doc.add(new Paragraph("Valor Unitário: R$ " + venda.getProduto().getPreco()));
			doc.add(new Paragraph("Total: " + venda.getTotal()));

			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Documento sem valor fiscal"));

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
