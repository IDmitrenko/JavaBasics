package main.java.GUI;

import java.awt.*;

// Менеджер вертикального расположения компонентов
class VerticalLayout implements LayoutManager
{
    private Dimension size = new Dimension();
    private int indent = 14;

    // Следующие два метода не используются (но они есть в интерфейсе и должны переопределяться)
    public void addLayoutComponent(String name, Component comp) {}
    public void removeLayoutComponent(Component comp) {}

    // Метод определения минимального размера для контейнера
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    // Метод определения предпочтительного размера для контейнера
    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    // Метод расположения компонентов в контейнере
    public void layoutContainer(Container container)
    {
        // Список компонентов
        Component list[] = container.getComponents();
        int currentY = indent;
        for(int i = 0; i < list.length; i++) {
            // Определение предпочтительного размера компонента
            Dimension pref = list[i].getPreferredSize();
            // Размещение компонента на экране
            list[i].setBounds(indent, currentY, pref.width, pref.height);
            // Учитываем промежуток в 5 пикселов между элементами
            currentY += indent;
            // Смещаем вертикальную позицию компонента
            currentY += pref.height;
        }
    }

    // Метод вычисления оптимального размера контейнера
    private Dimension calculateBestSize(Container c)
    {
        // Вычисление длины контейнера
        Component list[] = c.getComponents();
        int maxWidth = 0;
        for(int i = 0; i < list.length; i++) {
            int widht = list[i].getWidth();
            // Поиск компонента с максимальной длиной
            if(widht > maxWidth)
                maxWidth = widht;
        }

        // Размер контейнера в длину с учетом левого отступа
        size.width = maxWidth + indent;

        // Вычисление высоты контейнера
        int height = 0;
        for(int i = 0; i < list.length; i++) {
            height += indent;
            height += list[i].getHeight();
        }

        size.height = height;

        return size;
    }
}