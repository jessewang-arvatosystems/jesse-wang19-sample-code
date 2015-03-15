# Introduction #

BankTellerDatabaseTypeFile is a text file that the BankTeller program reads to configure to database type. There are three database types available, Treemap, ByteIO and Jdbc.


---

# Database Types in Depth #

  * Treemap: Will store data as long as the server is running. Once the server is shutdown all data will be lost.
  * ByteIO: Stores/Reads data onto a file named BankTellerByteOutput.txt in byte code.
  * Jdbc: Stores/Reads data from an online database.