# BaconFinder
## Description
Simple command line API scraper to look for bacon deals in the local area.

## Sample
```
Title: Meijer Sliced Bacon or Center Cut Bacon
	Store: Meijer
	Deal: sale $3.89
	Start: 6/26/2016 12:00:00 AM
	End: 7/2/2016 11:59:59 PM
Title: John Morrell Bacon or Nathan’s Beef Franks
	Store: Meijer
	Deal: sale $2.99
	Start: 6/26/2016 12:00:00 AM
	End: 7/2/2016 11:59:59 PM
```

## Usage
Bacon Only `java -jar baconFinder.jar`

Keyword search `java -jar baconFinder.jar chicken`

## Building
`sbt assembly` or `activator assembly`