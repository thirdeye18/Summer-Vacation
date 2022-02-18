package com.ChildrenOfSummer.SummerVacation.Client;

import com.ChildrenOfSummer.SummerVacation.GameEngine;
import com.ChildrenOfSummer.SummerVacation.Input;
import com.ChildrenOfSummer.SummerVacation.Util.SoundFX;

class Main {
    public static void main(String[] args) {
        //GameEngine game = new GameEngine();
        SoundFX.init(); //preload the sound files into memory
        //game.execute();
        Input.startMenu();
    }
}