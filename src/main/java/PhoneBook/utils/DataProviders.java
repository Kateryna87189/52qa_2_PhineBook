package PhoneBook.utils;

import PhoneBook.model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static PhoneBook.fw.ContactHelper.CONTACT_NAME;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{

                {"katranchik21@gmail.com", "Password@1"},
                {"katranchik21@gmail.com", "Password@1"},
                {"katranchik21@gmail.com", "Password@1"}

        };
    }

    @DataProvider
    public static Object[][] AddContactDataProvider() {
        return new Object[][]{
                {"ContactName1", "Lastname", "1234567890", "admin@gmail.com", "Germany, Hannover", "description"},
                {"ContactName2", "Lastname", "1234567890", "admin@gmail.com", "Germany, Hannover", "description"},
                {"ContactName3", "Lastname", "1234567890", "admin@gmail.com", "Germany, Hannover", "description"}


        };
    }

    @DataProvider
    public static Object[][] CreateAccountDataProvider() {
        return new Object[][]{
                {"katranchik" + System.currentTimeMillis() + "@gmail.com", "Password@1"}

        };
    }

    @DataProvider
    public Iterator<Object[]> iteratorDataProvider() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Name1", "LastName1", "1234567890", "admin1@gmail.com", "Hanover,Germany", "Description1"});
        list.add(new Object[]{"Name2", "LastName2", "1234567891", "admin2@gmail.com", "Hanover,Germany", "Description2"});
        list.add(new Object[]{"Name3", "LastName3", "1234567892", "admin3@gmail.com", "Hanover,Germany", "Description3"});
        list.add(new Object[]{"Name4", "LastName4", "1234567893", "admin4@gmail.com", "Hanover,Germany", "Description4"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> AddContactObjectProvider() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new Contact().setName("Name1").setLastname("LastName1").setPhone("1234567890").setEmail("admin1@gmail.com").setAddress("Germany, Berlin1").setDescription("Description1")});
        list.add(new Object[]{new Contact().setName("Name2").setLastname("LastName2").setPhone("1234567891").setEmail("admin2@gmail.com").setAddress("Germany, Berlin2").setDescription("Description2")});
        list.add(new Object[]{new Contact().setName("Name3").setLastname("LastName3").setPhone("1234567892").setEmail("admin3@gmail.com").setAddress("Germany, Berlin3").setDescription("Description3")});
        list.add(new Object[]{new Contact().setName("Name4").setLastname("LastName4").setPhone("1234567893").setEmail("admin4@gmail.com").setAddress("Germany, Berlin4").setDescription("Description4")});
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> addContactFromCsv() throws IOException {
        // Создаем список для хранения данных для тестов
        List<Object[]> list = new ArrayList<>();
        // Открываем CSV файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
        // Читаем первую строку из файла
        String line = reader.readLine();
        // Обрабатываем каждую строку файла до конца
        while (line != null) {
            // Разделяем строку на элементы по запятой
            String[] split = line.split(",");
            // Создаем объект Contact и устанавливаем его поля из прочитанных данных
            list.add(new Object[]{new Contact()
                    .setName(split[0])
                    .setLastname(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    .setDescription(split[5])
            });
            // Читаем следующую строку из файла
            line = reader.readLine();
        }
        // Закрываем файл после чтения всех данных
        reader.close();
        // Возвращаем итератор для списка объектов
        return list.iterator();
    }
}
