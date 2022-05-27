package com.howtodoinjava.demo;

import org.junit.Test;

import reactor.core.publisher.Mono;

public class AppTest {

	@Test
	public void test1() {
		Mono<Integer> m1 = Mono.just(10);
		m1.log().subscribe();
	}

	@Test
	public void test2() {
		Mono<Integer> m1 = Mono.just(10);
		m1.log().subscribe(element -> System.out.println("processing items" + element));
	}

	@Test
	public void test3() {
		Mono<Integer> m1 = Mono.just(10);
		m1.log().subscribe(element -> System.out.println("processing items" + element),
				ex -> System.out.println("exception occurred... " + ex.getMessage()),
				() -> System.out.println("finish processing..."));
	}

	@Test
	public void test4() {
		Mono m1 = Mono.error(new RuntimeException());
		m1.log().subscribe(element -> System.out.println("processing items" + element),
				ex -> System.out.println("exception occurred... " + ex),
				() -> System.out.println("finish processing..."));
	}

	@Test
	public void test5() {
		Mono<Integer> m1 = Mono.just(10);
		m1.log().doOnSubscribe(sub -> System.out.println("from doonsubscribe: " + sub))
				.doOnRequest(req -> System.out.println("from doonrequest: " + req))
				.doOnSuccess(val -> System.out.println("from doonsuccess " + val))
				.doOnError(er -> System.out.println("do on error  : " + er))
				.subscribe(element -> System.out.println("processing items" + element),
						ex -> System.out.println("exception occurred... " + ex),
						() -> System.out.println("finish processing..."));
	}

	@Test
	public void test6() {
		Mono m1 = Mono.error(new Exception());
		m1.log().doOnSubscribe(sub -> System.out.println("from doonsubscribe: " + sub))
				.doOnRequest(req -> System.out.println("from doonrequest: " + req))
				.doOnSuccess(val -> System.out.println("from doonsuccess " + val))
				.doOnError(er -> System.out.println("do on error  : " + er))
				.subscribe(element -> System.out.println("processing items" + element),
						ex -> System.out.println("exception occurred... " + ex),
						() -> System.out.println("finish processing..."));
	}

	@Test
	public void test7() {
		Mono m1 = Mono.error(new Exception());
		m1.onErrorReturn("abc").log().subscribe(el -> System.out.println("print element: " + el));

	}

	@Test
	public void test8() {
		Mono m1 = Mono.error(new Exception());
		m1.onErrorResume(el -> Mono.just("alternative value")).log().subscribe();

	}

}
