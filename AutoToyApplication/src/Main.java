import controller.ToyController;
import format.Format;
import format.ToyFormatTXT;
import model.*;
import validation.ToyValidatorTXT;
import view.ConsoleView;
import view.ToyViewCreator;
import view.View;

public class Main {
    public static void main(String[] args) {
        Format<Toy> format = new ToyFormatTXT();
        FilePersistent<Toy> filePersistent = new FilePersistent<>(format, "toys");
        ToyShop shop = new ToyShop(filePersistent);
        Automat<Toy> automat = new Automat<>(new FilePersistent<>(format, "prizes"));
        ToyController controller = new ToyController(shop, automat);
        ToyViewCreator creator = new ToyViewCreator(new ConsoleView(), new ToyValidatorTXT());
        View view = new ConsoleView(controller, creator);
        view.display();
    }
}
