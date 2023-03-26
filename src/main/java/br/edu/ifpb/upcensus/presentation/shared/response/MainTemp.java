package br.edu.ifpb.upcensus.presentation.shared.response;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class MainTemp {

	public static void main(String[] args) {
		Arrays.stream(HttpStatus.values())
			.map(status -> String.format("%s = '%s',", status.name(), status.toString()))
			.forEach(System.out::println);
	}

}
