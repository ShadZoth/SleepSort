package org.zoth.sleepsort;

/**
 * Класс, реализующий сортировку сном
 */
public class SleepSort {
    /**
     * Сортировка сном
     *
     * @param array сортируемый массив
     *
     * @return отсортированный массив
     *
     * @throws InterruptedException
     */
    public static int[] sleepSort(int[] array) throws InterruptedException {
        Thread[] sleepers = new Sleeper[array.length];
        Sleeper.result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            sleepers[i] = new Sleeper(array[i]);
            sleepers[i].start();
        }
        for (Thread sleeper : sleepers) {
            sleeper.join();
        }
        return Sleeper.result;
    }

    /**
     * Спящий
     */
    private static class Sleeper extends Thread {

        /**
         * Текущий элемент массива
         */
        static int current = 0;
        /**
         * Время сна Спящего
         */
        private final int value;
        /**
         * Массив результатов всех Спящих
         */
        static int[] result;

        /**
         * Создаёт нового спящего
         *
         * @param value время сна
         */
        private Sleeper(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(value * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            result[current] = value;
            current++;
        }
    }
}
