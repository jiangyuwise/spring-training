package com.codve.prospring.ch03;

public class BookWormOracle implements Oracle {
    private Encyclopedia encyclopedia;

    public BookWormOracle(Encyclopedia encyclopedia) {
        this.encyclopedia = encyclopedia;
    }

    @Override
    public String defineMeaningOfLife() {
        return "encyclopedia are a waste of money, go see the world instead.";
    }
}
