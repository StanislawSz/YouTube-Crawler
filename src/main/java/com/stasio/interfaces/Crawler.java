package com.stasio.interfaces;

import java.io.IOException;

public interface Crawler {
    ScanInfo scanPage(String URL) throws IOException;
}
