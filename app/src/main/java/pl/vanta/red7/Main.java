package pl.vanta.red7;


import pl.vanta.red7.engine.Coordinator;

public class Main {
    static void main(String[] args) {

        var coordinator = new Coordinator();
        coordinator.startGame("Alice", "Bob");
    }
}
