 #include <QPushButton>
#include <QHBoxLayout>
#include <QLineEdit>
#include <QTextEdit>
#include <QCheckBox>
#include <QButtonGroup>
#include <QGroupBox>
#include <QRadioButton>
#include <QGridLayout>

using namespace std;

class mainWindow: public QWidget
{//searchtext()->()text.tostdstring()

	Q_OBJECT
public:

	mainWindow(){
		quitButton = new QPushButton("&Quit");
		searchButton = new QPushButton("&Search");
		searchLine = new QLineEdit();
		websites = new QTextEdit();
		AND = new QRadioButton("AND");
		OR = new QRadioButton("OR");
		single = new QRadioButton("Single Word");
		sortPR = new QCheckBox("Sort By PageRank");
		sortAlpha = new QCheckBox("Sort By Alphabet")



		QGridLayout* mainLayout = new QGridLayout;
		//mainLayout->setAlignment(Qt::AlignLeft);

		QHBoxLayout* searchLayout = new QHBoxLayout;
		searchLayout ->addWidget(searchLine);
		searchLayout ->addWidget(searchButton);

		QHBoxLayout* quitLayout = new QHBoxLayout;
		quitLayout ->addWidget(AND);
		quitLayout ->addWidget(OR);
		quitLayout ->addWidget(single);
		quitLayout ->addWidget(sortPR);
		quitLayout ->addWidget(sortAlpha);
		quitLayout ->addWidget(quitButton);

		QVBoxLayout* findLayout = new QVBoxLayout;
		findLayout ->addWidget(websites);

		
		mainLayout->addLayout(searchLayout,1,0,1,150);
		mainLayout->addLayout(quitLayout, 3,0,1,150);
		mainLayout->addLayout(findLayout, 2,0,1,150);
		
      	setLayout(mainLayout);
      	
      	//setLayout(quitLayout);


      	connect(quitButton, SIGNAL(clicked()), this,SLOT(quitClick()));
      	connect(AND, SIGNAL(toggled()), this,SLOT();
      	connect(OR, SIGNAL(toggled()), this,SLOT();
      	connect(single, SIGNAL(toggled()), this,SLOT();

	}

	//~Search{}

	QSize sizeHint()const{
		return QSize(500,800);
	}

private slots:
	void quitClick(){close();}

	void ANDButton(){
		// Set<string> keywords = filterInput();

		// 	for (traverseUniverse = universe.begin(); traverseUniverse != universe.end(); ++traverseUniverse){
		// 		Set<string> intersect=  ((*traverseUniverse)->allWords()).setIntersection(keywords);
		// 		//intersect.print();
		// 		if(!intersect.empty()){
		// 			cout<<endl;
		// 			cout<<"PRINTING CORRESPONDING WEBPAGE:"<<endl;
		// 			cout<<**traverseUniverse<<endl;
		// 			cout<<"---------------------------"<<endl;
		// 			cout<<endl;

	}

	void ORButton(){

	}

	void singleButton(){

	}

private:
	QPushButton* quitButton;
	QPushButton* searchButton;
	QLineEdit* searchLine;
	QTextEdit* websites;
	QRadioButton* AND;
	QRadioButton* OR;
	QRadioButton* single;
	QCheckBox* sortPR;
	QCheckBox* sortAlpha;


};


// void Search::quitClick(){
// 	close();
// }