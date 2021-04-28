package br.com.github.felipexw.reactive;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class NotaFiscalSubscriber implements Subscriber<NotaFiscal> {

	private Subscription subscription;
	private final String name;

	public NotaFiscalSubscriber(String name) {
		super();
		this.name = name;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(NotaFiscal item) {
		new ProcessadorNotaFiscal().process(item);
		System.out.print("\n" + name);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("Some error occurred during processing. Cause:" + throwable.getCause());
	}

	@Override
	public void onComplete() {
		System.out.println("Everything was processed.");
	}

}
