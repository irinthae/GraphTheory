package formatter;

import java.util.List;


public class ListFormatter<E> {
    private List<E> list;
    private String formattedList;

    public ListFormatter(List<E> list) {
        this.list = list;
        setFormattedList();
    }

    private void setFormattedList() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (E element : list) {
            sb.append(element);
            if (list.indexOf(element) < list.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        this.formattedList = sb.toString();
    }

    @Override
    public String toString() {
        return formattedList;
    }
}
