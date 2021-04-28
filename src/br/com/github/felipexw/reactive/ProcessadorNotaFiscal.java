package br.com.github.felipexw.reactive;

public class ProcessadorNotaFiscal {

	public void process(NotaFiscal nf) {
		try {
			System.out.println("\n==== [STARTED] processing NF " + nf.info() + "... Thread name: "
					+ Thread.currentThread().getName());

			Thread.sleep(1250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(
				"\n===[FINISHED] processing NF " + nf.info() + "... Thread name: " + Thread.currentThread().getName());
	}

}
