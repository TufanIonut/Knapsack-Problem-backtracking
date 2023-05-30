import java.util.ArrayList;
import java.util.List;

class ItemGhiozdan {
    int greutate;
    int pret;

    ItemGhiozdan(int greutate, int pret) {
        this.greutate = greutate;
        this.pret = pret;
    }
}

public class ProblemaGhiozdanului {
    public static void main(String[] args) {
        // Exemplu de utilizare
        List<ItemGhiozdan> items = new ArrayList<>();
        items.add(new ItemGhiozdan(12, 4));
        items.add(new ItemGhiozdan(1, 2));
        items.add(new ItemGhiozdan(4, 10));
        items.add(new ItemGhiozdan(2, 2));
        items.add(new ItemGhiozdan(1, 1));
        int maxGreutate = 1;

        List<ItemGhiozdan> selectedItems = rezolvaGhiozdan(items, maxGreutate);
        System.out.println("Obiectele selectate pentru valoare maxima:");
        for (ItemGhiozdan item : selectedItems) {
            System.out.println("Greutate: " + item.greutate + ", Valoare: " + item.pret);
        }
    }

    public static List<ItemGhiozdan> rezolvaGhiozdan(List<ItemGhiozdan> items, int maxGreutate) {
        List<ItemGhiozdan> selectedItems = new ArrayList<>();
        backtrack(items, 0, 0, 0, maxGreutate, selectedItems, new ArrayList<>());
        return selectedItems;
    }

    public static void backtrack(List<ItemGhiozdan> items, int index, int currentGreutate, int currentpret,
                                 int maxGreutate, List<ItemGhiozdan> selectedItems, List<ItemGhiozdan> currentSelection) {
        if (currentGreutate > maxGreutate) {
            return;
        }

        if (index == items.size()) {
            if (currentpret > calculateTotalpret(selectedItems)) {
                selectedItems.clear();
                selectedItems.addAll(currentSelection);
            }
            return;
        }

        ItemGhiozdan currentItem = items.get(index);

        currentSelection.add(currentItem);
        backtrack(items, index + 1, currentGreutate + currentItem.greutate, currentpret + currentItem.pret,
                maxGreutate, selectedItems, currentSelection);
        currentSelection.remove(currentItem);
        backtrack(items, index + 1, currentGreutate, currentpret, maxGreutate, selectedItems, currentSelection);
    }

    public static int calculateTotalpret(List<ItemGhiozdan> items) {
        int totalpret = 0;
        for (ItemGhiozdan item : items) {
            totalpret += item.pret;
        }
        return totalpret;
    }
}
