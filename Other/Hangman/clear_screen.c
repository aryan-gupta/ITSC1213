#include <jni.h>
#include <stdio.h>
#include <Windows.h>
#include <conio.h>
#include "HangmanDriver.h"

// C:\Program Files\Java\jdk1.8.0_181\include
// C:\Program Files\Java\jdk1.8.0_181\include\win32

// gcc -I"C:\Program Files\Java\jdk1.8.0_181\include" -I"C:\Program Files\Java\jdk1.8.0_181\include\win32" -shared -o clear_screen.dll clear_screen.c
//^^ Does not work, use below https://stackoverflow.com/questions/44085213/cygwin-compilation-error-problematic-frame-c-cygwin1-dll0xd6d47-using-jni
// x86_64-w64-mingw32-g++.exe -I"C:\Program Files\Java\jdk1.8.0_181\include" -I"C:\Program Files\Java\jdk1.8.0_181\include\win32" -shared -o clear_screen.dll clear_screen.c

// unashamedly stolen from the windows documentation website
// https://docs.microsoft.com/en-us/windows/console/clearing-the-screen
// also system("cls"); is for babies
void cls( HANDLE hConsole ) {
	COORD coordScreen = { 0, 0 };    // home for the cursor 
	DWORD cCharsWritten;
	CONSOLE_SCREEN_BUFFER_INFO csbi; 
	DWORD dwConSize; 
	// Get the number of character cells in the current buffer. 
	if( !GetConsoleScreenBufferInfo( hConsole, &csbi )) {
		return;
	}

	dwConSize = csbi.dwSize.X * csbi.dwSize.Y;

	// Fill the entire screen with blanks.
	if( !FillConsoleOutputCharacter( hConsole,        // Handle to console screen buffer 
									 (TCHAR) ' ',     // Character to write to the buffer
									 dwConSize,       // Number of cells to write 
									 coordScreen,     // Coordinates of first cell 
									 &cCharsWritten ))// Receive number of characters written
	{
		return;
	}

	// Get the current text attribute.
	if( !GetConsoleScreenBufferInfo( hConsole, &csbi ))
	{
		return;
	}

	// Set the buffer's attributes accordingly.
	if( !FillConsoleOutputAttribute( hConsole,         // Handle to console screen buffer 
									 csbi.wAttributes, // Character attributes to use
									 dwConSize,        // Number of cells to set attribute 
									 coordScreen,      // Coordinates of first cell 
									 &cCharsWritten )) // Receive number of characters written
	{
		return;
	}

	// Put the cursor at its home coordinates.
	SetConsoleCursorPosition( hConsole, coordScreen );
}

void clear_screen(void) {
    HANDLE hStdout;
    hStdout = GetStdHandle(STD_OUTPUT_HANDLE);
    cls(hStdout);
}

JNIEXPORT void JNICALL Java_HangmanDriver_clear_1screen_1win_1api(JNIEnv *env, jclass thisClass) {
	clear_screen();
}