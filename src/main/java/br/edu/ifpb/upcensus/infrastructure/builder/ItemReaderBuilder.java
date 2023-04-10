package br.edu.ifpb.upcensus.infrastructure.builder;

import org.springframework.batch.item.ItemReader;

public class ItemReaderBuilder<R> {
	private ItemReader<R> reader;
	
	public ItemReader<R> buildCsvReader() {
		return null;
	}
}
