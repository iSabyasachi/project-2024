package org.example.functions;

import org.example.domain.vehicle.Car;
import org.example.util.function.Streams;
import org.example.util.function.CustomStream;

import java.util.List;
import java.util.stream.Stream;

public class ContainsAllOrAnyExample {
    /*
     * Using MyStream that extends Stream
     * */
    public static boolean checkIfContainsAllCarsUsingExtensionOfStream(List<Car> carInventoryList, List<Car> newCarList){
        Streams<Car> carInventoryStreams = Streams.from(carInventoryList.stream());
        return carInventoryStreams.containsAll(newCarList);
    }

    public static boolean checkIfContainsAllCarsUsingExtensionOfStream(List<Car> carInventoryList, Car... newCarList){
        Streams<Car> carInventoryStreams = Streams.from(carInventoryList.stream());
        return carInventoryStreams.containsAll(newCarList);
    }

    public static boolean checkIfContainsAllCarsUsingExtensionOfStream(List<Car> carInventoryList, Car newCar){
        Streams<Car> carInventoryStreams = Streams.from(carInventoryList.stream());
        return carInventoryStreams.containsAll(newCar);
    }

    public static boolean checkIfContainsAnyCarsUsingExtensionOfStream(List<Car> carInventoryList, List<Car> newCarList){
        Streams<Car> carInventoryStreams = Streams.from(carInventoryList.stream());
        return carInventoryStreams.containsAny(newCarList);
    }

    public static boolean checkIfContainsAnyCarsUsingExtensionOfStream(List<Car> carInventoryList, Car... newCarList){
        Streams<Car> carInventoryStreams = Streams.from(carInventoryList.stream());
        return carInventoryStreams.containsAny(newCarList);
    }


    /*
    * Using Streams functional interface
    * */
    public static boolean checkIfContainsAllCarsUsingCustomStream(List<Car> carInventoryList, Car... newCarList){
        CustomStream<Car> carInventoryStreamsT = CustomStream.from(carInventoryList.stream());
        return carInventoryStreamsT.containsAll(newCarList);
    }

    public static boolean checkIfContainsAllCarsUsingCustomStream(List<Car> carInventoryList, List<Car> newCarList){
        CustomStream<Car> carInventoryStreamsT = CustomStream.from(carInventoryList.stream());
        return carInventoryStreamsT.containsAll(newCarList);
    }

    public static boolean checkIfContainsAllCarsUsingCustomStream(List<Car> carInventoryList, Stream<Car> newCarList){
        CustomStream<Car> carInventoryStreamsT = CustomStream.from(carInventoryList.stream());
        return carInventoryStreamsT.containsAll(newCarList);
    }

    public static boolean checkIfContainsAnyCarsUsingCustomStream(List<Car> carInventoryList, Car... newCarList){
        CustomStream<Car> carInventoryStreamsT = CustomStream.from(carInventoryList.stream());
        return carInventoryStreamsT.containsAny(newCarList);
    }

    public static boolean checkIfContainsAnyCarsUsingCustomStream(List<Car> carInventoryList, List<Car> newCarList){
        return carInventoryList.containsAll(newCarList);
    }

    public static boolean checkIfContainsAnyCarsUsingCustomStream(List<Car> carInventoryList, Stream<Car> newCarList){
        CustomStream<Car> carInventoryStreamsT = CustomStream.from(carInventoryList.stream());
        return carInventoryStreamsT.containsAny(newCarList);
    }

}
