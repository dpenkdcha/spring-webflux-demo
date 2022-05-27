package com.howtodoinjava.demo;

import java.time.Duration;
import java.util.Arrays;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AppTestFlux {

	@Test
	public void test1() {
		Flux<String> f1 = Flux.just("swimming", "running", "cycling");
		f1.log().subscribe();
	}

	@Test
	public void test2() {
		Flux<String> f1 = Flux.just("swimming", "running", "cycling");
		f1.log().subscribe((h) -> System.out.println("Hobby: " + h),
				(exception) -> System.out.println("exception occurred : " + exception.getLocalizedMessage()),
				() -> System.out.println("processed all information"));
	}

	@Test
	public void test3() {
		Flux<String> f1 = Flux.fromIterable(Arrays.asList("java", "aws", "angular", "react", "nodejs"));
		f1.log().subscribe((h) -> System.out.println("Skill: " + h),
				(exception) -> System.out.println("exception occurred : " + exception.getLocalizedMessage()),
				() -> System.out.println("processed all information"));
	}

	@Test
	public void test4() {
		Flux<Integer> f1 = Flux.range(40654, 10);
		f1.log().subscribe((h) -> {
			String empid = "Acc-" + h;
			System.out.println("employee id: " + empid);
		}, (exception) -> System.out.println("exception occurred : " + exception.getLocalizedMessage()),
				() -> System.out.println("processed all information"));
	}

	@Test
	public void test5() throws Exception {
		Flux<Long> f1 = Flux.interval(Duration.ofSeconds(1));
		f1.log().subscribe((h) -> {
			String empid = "Acc-" + h;
			System.out.println("employee id: " + empid);
		});
		Thread.sleep(5000);
	}

	@Test
	public void test6() throws Exception {
		Flux<Long> f1 = Flux.interval(Duration.ofSeconds(1));
		f1.log().take(3).subscribe((id) -> System.out.println("employee id: " + id));
		Thread.sleep(6000);
	}

	@Test
	public void test7() throws Exception {
		Flux<String> f1 = Flux.just("A", "B", "C");
		StepVerifier.create(f1.log()).expectNext("A").expectNext("B").expectNext("C").verifyComplete();
	}

	@Test
	public void test8() throws Exception {
		Flux<String> f1 = Flux.just("A", "B", "C", "D");
		StepVerifier.create(f1.log()).expectNextCount(4).verifyComplete();
	}

	@Test
	public void test9() throws Exception {
		Flux<String> f1 = Flux.just("A", "B", "C", "D").concatWith(Flux.error(new RuntimeException()));
		StepVerifier.create(f1.log()).expectNextCount(2).expectNext("C").expectNext("D")
				.expectError(RuntimeException.class).verify();
	}

	// convert flux of integer into flux of string
	@Test
	public void test10() throws Exception {
		Flux<Integer> f1 = Flux.range(25, 5);

		Flux<String> f2 = f1.map(id -> "Acc-" + id);

		f2.subscribe(i -> System.out.println(i));
	}

	// need of flatmap
	@Test
	public void test11() throws Exception {
		Flux<Integer> f1 = Flux.range(25, 5);
		// i want to convert flux of integer into flux of string
		Flux<Object> f2 = f1.map(id -> Flux.range(id * 5, 2));
		f2.subscribe(x -> System.out.println(x));
	}

	// solution using flatmap
	@Test
	public void test12() throws Exception {
		Flux<Integer> f1 = Flux.range(25, 5);
		// i want to convert flux of integer into flux of string
		Flux<Object> f2 = f1.flatMap(id -> Flux.range(id * 5, 2));
		f2.subscribe(x -> System.out.println(x));
	}

	// mono into flux
	@Test
	public void test13() throws Exception {
		Mono<Integer> m1 = Mono.just(6);
		Flux<Object> f1 = m1.flatMapMany(count -> Flux.range(200, count));
		f1.subscribe(id -> System.out.println(id));
	}

	@Test
	public void test14() throws Exception {
		Flux<Integer> f1 = Flux.range(10, 5);
		Flux<Integer> f2 = Flux.range(50, 5);

		Flux<Integer> concat = Flux.concat(f1, f2);
		concat.subscribe(val -> System.out.println(val));
	}

	@Test
	public void test15() throws Exception {
		Flux<Integer> f1 = Flux.range(10, 5).delayElements(Duration.ofMillis(500));
		Flux<Integer> f2 = Flux.range(50, 5).delayElements(Duration.ofMillis(400));

		Flux<Integer> concat = Flux.concat(f1, f2);
		concat.subscribe(val -> System.out.println(val));

		Thread.sleep(5000);
	}

	@Test
	public void test16() throws Exception {
		Flux<Integer> f1 = Flux.range(10, 5).delayElements(Duration.ofMillis(400));
		Flux<Integer> f2 = Flux.range(50, 5).delayElements(Duration.ofMillis(500));

		Flux<Integer> concat = Flux.merge(f1, f2);
		concat.log().subscribe(val -> System.out.println(val));

		Thread.sleep(6000);
	}
	// zip
	// zipwith

	// backpressure
	@Test
	public void test17() throws Exception {
		Flux<Integer> f1 = Flux.range(501, 100);

		f1.log().subscribe(e -> System.out.println(e), // consumer
				null, // exception handling
				null, // complete
				sub -> sub.request(10) // subscription
		);
	}

	@Test
	public void test18() throws Exception {
		Flux<Integer> f1 = Flux.range(500, 100);

		f1.log().limitRate(15).subscribe();
	}
}
