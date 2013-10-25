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
        int min = findMin(array);
        Thread[] sleepers = new Sleeper[array.length];
        Sleeper.result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            sleepers[i] = new Sleeper(array[i], (array[i] - min) * 100);
            sleepers[i].start();
        }
        for (Thread sleeper : sleepers) {
            sleeper.join();
        }
        System.out.println();
        return Sleeper.result;
    }

    /**
     * Ищет минимальный элемент массива
     *
     * @param array массив
     *
     * @return минимальный элемент
     */
    private static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
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
         * Значение Спящего
         */
        private final int value;
        /**
         * Массив результатов всех Спящих
         */
        static int[] result;
        /**
         * Время сна Спящего
         */
        private final int sleepTime;

        /**
         * Создаёт нового спящего
         *
         * @param value     значение
         * @param sleepTime время сна
         */
        private Sleeper(int value, int sleepTime) {
            this.value = value;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
                System.out.print("|");//Успокаивающая строка
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            result[current] = value;
            current++;
        }
    }
}
