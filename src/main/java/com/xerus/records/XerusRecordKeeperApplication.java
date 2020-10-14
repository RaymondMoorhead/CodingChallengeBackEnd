/*! \file XerusRecordKeeperApplication.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Start point for application
*/

package com.xerus.records;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//!  The entry point class for the application
public class XerusRecordKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(XerusRecordKeeperApplication.class, args);
	}

}
