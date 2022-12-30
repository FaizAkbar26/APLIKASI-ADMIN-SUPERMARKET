import java.util.ArrayList;
public class Main {
    public static final int ADD_CUSTOMER = 1;
    public static final int ADD_ORDER = 2;
    public static final int ADD_PRODUCT = 3;
    public static final int LIST_CUSTOMERS = 4;
    public static final int QUIT = 10;
    private Input in = new Input();
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;

    public Main()
    {
        customers = new ArrayList<Customer>();
        products = new ArrayList<Product>();
    }

    public void run()
    {
        while(true)
        {
            displayMenu();
            int option = getMenuInput();
            if (option == QUIT)
            {
                break;
            }
            doOption(option);
        }
    }

    private void displayMenu()
    {
        System.out.println("APLIKASI ADMIN SUPERMARKET");
        System.out.println(ADD_CUSTOMER + ". Tambahkan Customer");
        System.out.println(ADD_ORDER + ". Tambahkan Order");
        System.out.println(ADD_PRODUCT + ". Tambahkan Product");
        System.out.println(LIST_CUSTOMERS + ". List Customers");
        System.out.println();
        System.out.println(QUIT + ". Keluar");
    }

    private void doOption(int option)
    {
        switch (option)
        {
            case ADD_CUSTOMER:
                addCustomer();
                break;
            case ADD_ORDER:
                addOrder();
                break;
            case ADD_PRODUCT:
                addProduct();
                break;
            case LIST_CUSTOMERS:
                listCustomers();
                break;
            default:
                System.out.println("Pilihan Invalid - Ulang Kembali");
        }
    }

    private int getMenuInput()
    {
        System.out.print("Masukkan Pilihan Menu : ");
        int option = in.nextInt();
        in.nextLine();
        return option;
    }

    private void addCustomer()
    {
        System.out.println("Tambahkan customer baru");
        System.out.println("Masukkan nama pertama :");
        String firstName = in.nextLine();
        System.out.println("Masukkan nama terakhir :");
        String lastName = in.nextLine();
        System.out.println("Masukkan alamat :");
        String address = in.nextLine();
        System.out.println("Masukkan nomor telepon :");
        String phone = in.nextLine();
        System.out.println("Masukkan email :");
        String email = in.nextLine();
        Customer customer = new Customer(firstName,lastName,address,phone,email);
        customers.add(customer);
    }

    private void addOrder()
    {
        Customer customer = findCustomer();
        if (customer == null)
        {
            System.out.println("Tidak bisa menambahkan order");
            return;
        }
        Order order = new Order();
        addLineItems(order);
        if (order.getLineItemCount() == 0)
        {
            System.out.println("Pesanan tidak boleh kosong");
            return;
        }
        customer.addOrder(order);
    }

    private Customer findCustomer()
    {
        System.out.print("Masukkan nama terakhir customer : ");
        String lastName = in.nextLine();
        System.out.print("Masukkan nama pertama customer : ");
        String firstName = in.nextLine();
        return getCustomer(lastName, firstName);
    }

    private Customer getCustomer(String lastName, String firstName)
    {
        for (Customer customer : customers)
        {
            if (customer.getLastName().equals(lastName)
                    && customer.getFirstName().equals(firstName))
            {
                return customer;
            }
        }
        return null;
    }

    private void addLineItems(Order order)
    {
        while (true)
        {
            System.out.print("Masukkan item (y/n): ");
            String reply = in.nextLine();
            if (reply.startsWith("y"))
            {
                LineItem item = getLineItem();
                if (item != null)
                {
                    order.add(item);
                }
            }
            else
            {
                break;
            }
        }
    }

    private LineItem getLineItem()
    {
        System.out.print("Masukkan product code: ");
        int code = in.nextInt();
        in.nextLine();
        Product product = getProduct(code);
        if (product == null)
        {
            System.out.println("Produk kode Invalid");
            return null;
        }
        System.out.print("Masukkan kuantitas: ");
        int quantity = in.nextInt();
        in.nextLine();
        return new LineItem(quantity,product);
    }

    private Product getProduct(int code)
    {
        for (Product product : products)
        {
            if (product.getCode() == code)
            {
                return product;
            }
        }
        return null;
    }

    private void addProduct()
    {
        System.out.print("Masukkan kode produk: ");
        int code = in.nextInt();
        in.nextLine();
        if (!isAvailableProductCode(code))
        {
            return;
        }
        System.out.print("Masukkan deskripsi produk : ");
        String description = in.nextLine();
        System.out.print("Masukkan harga produk : ");
        int price = in.nextInt();
        in.nextLine();
        Product product = new Product(code,description,price);
        products.add(product);
    }

    private boolean isAvailableProductCode(int code)
    {
        if (code < 1)
        {
            return false;
        }
        for (Product product : products)
        {
            if (product.getCode() == code)
            {
                return false;
            }
        }
        return true;
    }

    public void listCustomers()
    {
        System.out.println("List customers");
        for (Customer customer : customers)
        {
            System.out.println("Nama                : " + customer.getLastName()
                    + ", "
                    + customer.getFirstName());
            System.out.println("Alamat              : " + customer.getAddress());
            System.out.println("Phone               : " + customer.getPhone());
            System.out.println("Email               : " + customer.getEmail());
            System.out.println("Pesanan yang dibuat : " + customer.getOrders().size());
            System.out.println("Total orders        : " + customer.getTotalForAllOrders());
        }
    }

    public static void main(String[] args)
    {
        Main orderSystem = new Main();
        orderSystem.run();
    }
}
