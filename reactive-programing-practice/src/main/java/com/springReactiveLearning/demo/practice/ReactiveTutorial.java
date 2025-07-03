package com.springReactiveLearning.demo.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private int id;
    private String fristName;
    private String lastName;
}
public class ReactiveTutorial {
    // tesing Mono class as empty and send one
    private Mono<String> testMono(){
        return Mono.justOrEmpty(null);
    }

    // tesing flex for sending list of student name using flux froom 0 to n from iterator
    private Flux<String> testFlux(){
//        List.of("sahil","amit","rahul");
        Flux<String> flux=Flux.fromIterable(List.of("sahil","amit","rahul")).log();
        return flux.map(s-> s.toUpperCase(Locale.ROOT));
//        return Flux.just("sahil","amit","rahul").log();
    }
    // creating stream of numbers inn words
    public static Stream<String> StringNumbersStream(){
        return Stream.of("one","two","three","four","five","six","seven","eight","nine","zero");
    }
    // it will print even numbers
    public static Stream<Integer> intNumberStream(){
        return Stream.iterate(0,i -> i+2)
                .limit(10);
    }


    // creating user stream
    public static Stream<User> userStream(){
        return Stream.of(
                new User(1,"sahil","kumar"),
                new User(2,"sumit","kumar"),
                new User(3,"alekh","kumar")
        );
    }

    public static void main(String[] args) {
        ReactiveTutorial reactiveTutorial=new ReactiveTutorial();
//        reactiveTutorial.testMono()
//                .subscribe(System.out::print);
//        reactiveTutorial.testFlux()
//                .subscribe(System.out::println);

        // it show all the element of stream
         ReactiveTutorial.intNumberStream().forEach(e->System.out.println(e));

        // check elements less then 5 using stream filter
        // ReactiveTutorial.intNumberStream().filter(number-> number<5).forEach(System.out::println);

        // print 2nd and 3rd values from intnumberstream which are greather then 5
        // ReactiveTutorial.intNumberStream().filter(n->n>5).skip(1).limit(2)
        //      .forEach(e-> System.out.println(e));

        // print the first number of intnumberstream which is greater then 5 if nothing present return 1
        // Integer value = ReactiveTutorial.intNumberStream().filter(n->n>5).findFirst().orElse(-1);
        // System.out.println(value);

        // print all user first name from userStream
        // ReactiveTutorial.userStream().forEach(e-> System.out.println(e.getFristName()));
        // ReactiveTutorial.userStream().map(user-> user.getFristName())
        //      .forEach(userName -> System.out.println(userName));

//        ReactiveTutorial.intNumberStream()
//                .flatMap(id->ReactiveTutorial.userStream().filter(user -> user.getId()==id))
//                .map(user-> user.getFristName())
//                .forEach(user -> System.out.println(user));

    }

}
