package controller;

import com.badlogic.gdx.graphics.Texture;

import actor.Enemy;

/**
 * Created by mikael.mathis on 01.12.2015.
 */
public final class EnemyHandler {

    public static Enemy createNewEnemy(){

        return new Enemy("EnemyName", 100000);

    }

    public static Texture createNewEnemyTexture(){
        int r = (int)(Math.random() * 2) + 1;
        if(r == 1){
            return new Texture("zombieCow.png");
        }
        else{
            return new Texture("bear.png");
        }
    }

    public static void damageEnemy(Enemy enemy){
        enemy.setLifePoints(enemy.getLifePoints() - 5000);
    }

    public static double calculateWinExp(){
        return 1000;
    }

    public static double calculateWinMoney(){
        return 500;
    }

}
