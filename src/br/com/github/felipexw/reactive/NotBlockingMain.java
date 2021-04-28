package br.com.github.felipexw.reactive;

import java.util.Scanner;
import java.util.concurrent.SubmissionPublisher;

public class NotBlockingMain {

	public static void main(String[] args) {
		final var publisher = new SubmissionPublisher<NotaFiscal>();
		final var startedAt = System.currentTimeMillis();
		System.out.println("Start");

		publisher.subscribe(new NotaFiscalSubscriber("Subscriber 1"));
		publisher.subscribe(new NotaFiscalSubscriber("Subscriber 2"));
		publisher.subscribe(new NotaFiscalSubscriber("Subscriber 3"));

		publisher.submit(new NotaFiscal("NF 1"));
//		publisher.submit(new NotaFiscal("NF 2"));
//		publisher.submit(new NotaFiscal("NF 3"));

		final var scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();

		publisher.close();
		final var elapsedTime = System.currentTimeMillis() - startedAt;
		System.out.println("Finished at " + elapsedTime);

	}

	private void withoutPubSub() {
		final var publisher = new SubmissionPublisher<NotaFiscal>();
		final var startedAt = System.currentTimeMillis();
		System.out.println("Start");

		publisher.consume(new ProcessadorNotaFiscal()::process);
		publisher.consume(new ProcessadorNotaFiscal()::process);
		publisher.consume(new ProcessadorNotaFiscal()::process);
		publisher.consume(new ProcessadorNotaFiscal()::process);

		publisher.submit(new NotaFiscal("NF 1"));
		publisher.submit(new NotaFiscal("NF 2"));
		publisher.submit(new NotaFiscal("NF 3"));

		final var scanner = new Scanner(System.in);
		System.out.println("Finalizar?");
		scanner.nextLine();
		scanner.close();

		publisher.close();
		final var elapsedTime = System.currentTimeMillis() - startedAt;
		System.out.println("Finished at " + elapsedTime);
	}
}
