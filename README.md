# pendrive-control

A basic JavaFX application to collect datas from employee's field work.
It collects the PC's lastbootuptime, motherboard-serial, and from a third-party API the location with relatively high accuracy.
First the App collects the serialnumbers of the connected pendrives and than send a GET request to a microservice for all employee's pendrive-serials.
If the employee's pendrive is connected the GUI launches and the employee can select the work type, than the employee writes the log and sends it to an another microservice.
