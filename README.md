Markdown
# Dokumentasi Project Akhir PBO - Sistem Kasir (POS)

Project ini adalah aplikasi desktop berbasis Java Swing yang menerapkan konsep PBO (Pemrograman Berorientasi Objek), multithreading, terhubung dengan database MySQL, dan memiliki fitur I/O untuk mencetak struk belanja secara otomatis.

## Struktur Folder Project

```text
src/
 ├── main/
 │    └── Main.java                 # Entry point aplikasi (Multithreading EDT)
 ├── database/
 │    └── DatabaseConnection.java   # Konfigurasi koneksi JDBC ke MySQL
 ├── model/
 │    ├── Product.java              # Superclass (Produk) - Abstract
 │    ├── FoodProduct.java          # Subclass (Makanan)
 │    ├── DrinkProduct.java         # Subclass (Minuman)
 │    ├── Transaction.java          # Model untuk data history transaksi
 │    └── CartItem.java             # Model penyimpan data keranjang belanja
 ├── controller/
 │    ├── ProductController.java    # Logika CRUD Produk ke tabel 'products'
 │    └── TransactionController.java# Logika bayar banyak barang, update stok, & cetak struk
 └── view/
      └── MainView.java             # Antarmuka grafis (GUI Java Swing)
Penjelasan Konsep OOP & Java yang Digunakan
Class dan Object:

Class bertindak sebagai blueprint (contoh: Product.java, CartItem.java).

Object adalah instansiasi class dari data yang dimanipulasi user di layar GUI.

Encapsulation (Enkapsulasi):

Di package model, semua variabel dideklarasikan sebagai private atau protected.

Akses dan modifikasi data wajib menggunakan method Getter (getNamaProduk()) dan Setter (setNamaProduk()).

Inheritance (Pewarisan):

FoodProduct dan DrinkProduct menggunakan keyword extends Product.

Class anak mewarisi atribut dan method dari class induk, menghindari penulisan kode berulang.

Polymorphism:

Terjadi Overriding method getProductInfo() di class FoodProduct dan DrinkProduct.

Multithreading:

Digunakan di dua tempat: SwingUtilities.invokeLater pada Main.java untuk memisahkan proses layar (EDT) dari main thread, dan penggunaan Thread di MainView.java untuk menampilkan Jam Real-time agar aplikasi tidak freeze.

File I/O (Input/Output):

Sistem menggunakan class FileWriter dan PrintWriter di dalam TransactionController untuk meng-generate file .txt secara otomatis sebagai struk belanja ketika transaksi berhasil.

Collection (ArrayList):

Menggunakan ArrayList<Product>, ArrayList<Transaction>, dan ArrayList<CartItem> sebagai tempat penyimpanan data yang dinamis sebelum di-render ke JTable atau diproses ke database.

Penjelasan Setiap Class
Main.java: Menjalankan aplikasi dengan memanggil dan menampilkan form GUI dengan aman.

DatabaseConnection.java: Menyimpan kredensial database dan menggunakan Driver Manager JDBC.

Product / FoodProduct / DrinkProduct: Blueprint data barang jualan.

Transaction.java: Mempresentasikan satu baris data riwayat penjualan.

CartItem.java: Menyimpan data barang yang sedang di-scan kasir sebelum pembayaran dilakukan.

ProductController.java: Melakukan eksekusi Prepared Statements untuk Query SQL (INSERT, SELECT, UPDATE, DELETE) ke tabel produk.

TransactionController.java: Jantung sistem kasir. Menangani looping keranjang belanja, proses setAutoCommit(false) dan rollback() untuk mencegah stok berkurang jika terjadi error, serta memanggil logika pembuatan struk teks.

MainView.java: Tampilan GUI yang membagi layar menjadi 3 Tab. Menangani Event Listener saat pengguna mengetik angka atau menekan tombol.

Cara Menjalankan Project
Pastikan Anda sudah menginstall XAMPP dan menyalakan MySQL.

Buat database baru bernama kasir_db di phpMyAdmin.

Salin dan jalankan isi dari file database.sql untuk membuat tabel products dan transactions.

Buka NetBeans IDE dan buka project ini.

Pastikan library MySQL JDBC Driver (mysql-connector-java) sudah ditambahkan di folder Libraries project Anda.

Klik kanan pada class Main.java lalu pilih Run File (atau tekan Shift+F6).

Alur Penggunaan Aplikasi (GUI)
Aplikasi memiliki 3 Tab utama:

Tab 1: Kelola Produk (Gudang)

Input form (ID otomatis, Nama, Harga, Stok, Kategori) untuk Tambah, Edit, dan Hapus barang.

Tabel yang diklik akan otomatis mengisi form di atasnya.

Fitur pencarian barang berdasarkan nama.

Tab 2: Transaksi Kasir (Front Office)

Sistem Keranjang: Pilih barang dari combo box, isi jumlah, lalu klik "Tambah ke Keranjang". Barang akan masuk ke tabel keranjang.

Pembayaran: Kasir menginput jumlah Uang Tunai dari pelanggan.

Saat klik "Proses Bayar", sistem otomatis mengecek stok, menghitung kembalian, memotong stok di database, dan mencetak file struk_YYYYMMDD_HHMMSS.txt ke dalam folder project.

Tab 3: Riwayat Transaksi

Menampilkan daftar semua transaksi masa lalu berserta timestamp (waktu spesifik) penjualan.
