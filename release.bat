@echo off
echo Creating Digital Library release package...

:: Clean and build
call mvn clean package

:: Create release directory
rmdir /s /q release 2>nul
mkdir release

:: Copy files
copy target\digital-library-1.0.0.jar release\
copy README.md release\
copy LICENSE release\

:: Create run script
echo @echo off > release\run.bat
echo java -jar digital-library-1.0.0.jar >> release\run.bat

:: Create ZIP
powershell Compress-Archive -Path release\* -DestinationPath digital-library-v1.0.0.zip -Force

echo Release package created successfully!