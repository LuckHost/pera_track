package com.luckhost.peratrack.presentation.mainScreen.ui

/**
 * Источник
 * https://habr.com/ru/articles/730924/
 *
 * Представляет собой модель хранения смежной информации о рисуемом объекте диаграммы.
 *
 * @property percentOfCircle - значение занимаемого процента круговой диаграммы,
 * откуда должен начать отрисовываться объект.
 */
data class PieChartElement(
    var itemName: String,
    var percentOfCircle: Float,
) {
    /**
     * Блок, в котором значения преобразуются к приближенным значениям круговой диаграммы.
     * То есть в модель передается процент (от 0 до 100).
     */
    init {
        // Проверка на корректность переданного процента.
        if (percentOfCircle < 0 || percentOfCircle > 100) {
            throw IllegalArgumentException(
                "percentOfCircle must been between 0 and 100, but was $percentOfCircle")
        }

        // Расчет переданного значения на круговой диаграмме.
        percentOfCircle = 360 * percentOfCircle / 100
    }
}
