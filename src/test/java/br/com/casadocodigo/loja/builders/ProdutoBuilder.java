package br.com.casadocodigo.loja.builders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.casadocodigo.loja.model.Preco;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

public class ProdutoBuilder {

	
	private List<Produto> produtos = new ArrayList<>();
	
	private ProdutoBuilder(Produto produto) {
		produtos.add(produto);
	}
	
	public static ProdutoBuilder newProduto(TipoPreco tipoPreco, BigDecimal valor) {
		Produto livro = create("Book 1", tipoPreco, valor);
		return new ProdutoBuilder(livro);
	}
	
	public static ProdutoBuilder newProduto() {
		Produto livro = create("Book 1", TipoPreco.COMBO, BigDecimal.TEN);
		return new ProdutoBuilder(livro);
	}

	private static Produto create(String titulo, TipoPreco tipoPreco, BigDecimal valor) {
		Produto livro = new Produto();
		livro.setTitulo(titulo);
		livro.setDataLancamento(Calendar.getInstance());
		livro.setPaginas(150);
		livro.setDescricao("great book about testing!");
		Preco preco = new Preco();
		preco.setTipo(tipoPreco);
		preco.setValor(valor);
		livro.getPrecos().add(preco);
		
		return livro;
	}
	
	public ProdutoBuilder mais(int quantidade) {
		Produto base = produtos.get(0);
		Preco preco = base.getPrecos().get(0);
		for(int i = 0; i <  quantidade; i++) {
			produtos.add(create("Book "+i, preco.getTipo(), preco.getValor()));
		}
		return this;
	}
	
	public Produto buildOne() {
		return produtos.get(0);
	}
	
	public List<Produto> buildAll() {
		return produtos;
	}
	
}