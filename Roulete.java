package toydraw;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import toydraw.Toys.Car;
import toydraw.Toys.Doll;
import toydraw.Toys.Gun;
import toydraw.Toys.Ball;
import toydraw.Toys.Soldier;

/**
 * Roulete
 */
public class Roulete {

    public static void main(String[] args) throws IOException {
        try (Scanner readScanner = new Scanner(System.in)) {

            PrizeBox box = new PrizeBox();
            boolean active = true;
            box.FillBox();
            System.out.println("Игровой автомат Игрушки");
            while (active) {
                System.out.println("______________________________________________________________");

                System.out.println(
                        "Выберите действие\n1-Посмотреть витрину\n2-Обновить витрину\n3-Собрать свою витрину\n4-Выиграть приз\n5-Выйти\n");
                System.out.println("Ваша команда:");
                int command = readScanner.nextInt();
                switch (command) {
                    case 1:
                        System.out.println(box.printBox());
                        break;
                    case 2:
                        box.clearBox();
                        box.FillBox();
                        break;
                    case 3:
                        box.clearBox();
                        Boolean edit = true;
                        while (edit) {
                            System.out.println("Ваша витрина:");
                            System.out.println(box.printBox());
                            System.out.println("______________________________________________________________");
                            System.out.println(
                                    "Что хотите добавить\n1-Пулю\n2-Куклу\n3-Солдатика\n4-Кубик Рубика\n5-Вызрыватку\n6-Закончить набор игрушек");
                            System.out.println("Ваша команда:");
                            int whatToAdd = readScanner.nextInt();
                            switch (whatToAdd) {
                                case 1:
                                    box.addToy(new Car());
                                    break;
                                case 2:
                                    box.addToy(new Doll());
                                    break;
                                case 3:
                                    box.addToy(new Soldier());
                                    break;
                                case 4:
                                    box.addToy(new Ball());
                                    break;
                                case 5:
                                    box.addToy(new Gun());
                                    break;
                                case 6:
                                    edit = false;
                                    System.out.println("Ваша витрина:");
                                    System.out.println(box.printBox());
                                    break;
                                default:
                                    System.out.println("Неверная команда");
                                    break;
                            }

                        }
                    case 4:

                        int prizeIndex = box.dropPrize();
                        if (prizeIndex == -1) {
                            System.out.println("игрушек на витрине нет");
                            break;
                        }
                        try (FileWriter writer = new FileWriter("YourPrize.txt")) {
                            writer.write(box.getInside().get(prizeIndex).getTitle());
                        }
                        System.out.println("Вы выиграли " + box.getInside().get(prizeIndex).getTitle());
                        System.out.println("Он находится в YourPrize");
                        box.removeItem(prizeIndex);
                        break;
                    case 5:
                        System.out.println("До свидания!");
                        active = false;
                        break;

                    default:
                        System.out.println("Неверная команда, повторите");
                        break;
                }

            }
        }

    }
}