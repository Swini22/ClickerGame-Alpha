package controller;

import actor.Enemy;

/**
 * Created by mikael.mathis on 01.12.2015.
 */
public final class EnemyHandler {

    public static Enemy createNewEnemy(){

        Enemy enemy = new Enemy(10000);
        return enemy;

    }

    public static void damageEnemy(Enemy enemy){
        enemy.setLifePoints(enemy.getLifePoints() - 5000);
    }

    public static double calculateDamage(double damage, Enemy enemy){
        return 0;
    }

    public static double calculateWinExp(){
        return 0;
    }

    public static double calculateWinMoney(){
        return 0;
    }

}
