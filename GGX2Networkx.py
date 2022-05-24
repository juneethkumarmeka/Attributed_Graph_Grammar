"""
Created on Thu Feb 10 22:30:43 2022

@author: JuneethKumar Meka

Project Name : Graph Grammar Attribute Benchmark Generator
"""

#Modules
#-----------------------------------------------------------------------------#
import xml.etree.ElementTree as ET
import networkx as nx 
#-----------------------------------------------------------------------------#

#Source Code
#-----------------------------------------------------------------------------#
class NodeStorage:
    def __init__(self,ID):
        self.nodeID = ID 
        self.gatename =  None 
        self.gateID =  None 
        
    def addNodeName(self,nodename):
        self.nodename  = nodename
        
    def addGateName(self,gatename):
        self.gatename = gatename 
    
    def addGateID(self,gateID):
        self.gateID = gateID 
        
    def getNodeName(self): 
        return self.nodename
    
    def getNodeID(self): 
        return self.nodeID
    
    def getGateName(self): 
        return self.gatename
    
    def getGateID(self): 
        return self.gateID
    
        
    
        
class GGX2Networkx:
    """
        Extracts the Host Graph of the GGX file 
        input : 
            ggxfile : file name 
    """
    def __init__(self,ggxfile):
        self.file = ggxfile 
        self.nodeTypes = {}
        self.graph = nx.DiGraph()
        self.parsing()
    
    def parsing(self):
        """
            Parses the GGX file 
        """
        tree = ET.parse(self.file)
        root =   tree.getroot()
        
        for rootchild in root :
            for eachrootchild in rootchild :
                if eachrootchild.tag == "Types":
                    for eachlevel2 in eachrootchild:
                        if eachlevel2.tag == "NodeType":
                            nodeID = eachlevel2.attrib["ID"]
                            self.nodeTypes[nodeID] = NodeStorage(nodeID)
                            nodename = eachlevel2.attrib["name"].split("%")[0]
                            self.nodeTypes[nodeID].addNodeName(nodename)
                            for eachlevel3 in eachlevel2:
                                if (eachlevel3.tag == "AttrType" and eachlevel3.attrib["attrname"] == "gateType") :
                                    gateID = eachlevel3.attrib["ID"]
                                    self.nodeTypes[nodeID].addGateID(gateID)
                                    break
                                
                                    
                            
                if eachrootchild.tag == "Graph":
                    for each in eachrootchild:
                        if each.tag == "Node":
                            nodename = each.attrib["ID"]
                            nodeID = each.attrib["type"]
                            nodetypedata = self.nodeTypes[nodeID]
                            if nodetypedata.getGateID() == None:
                                nodetypedata.addGateName(nodetypedata.getNodeName())
                            else:
                                for eachlevel2 in each:
                                    if(eachlevel2.attrib["type"] == nodetypedata.getGateID()):
                                        gatename = str(eachlevel2[0][0].text).lower()
                                        nodetypedata.addGateName(gatename)
                        
                            self.graph.add_node(nodename,type = nodetypedata.getGateName())
                            
                        elif each.tag == "Edge":
                            source = each.attrib["source"]
                            target = each.attrib["target"]
                            self.graph.add_edge(source, target)
                        
                

    def getGraph(self):
        """
            Returns the networkx graph
        """
        return self.graph
#-----------------------------------------------------------------------------#

#Testing
#-----------------------------------------------------------------------------#
# g1 = GGX2Networkx("LevelConnectionBasedGenerator_out.ggx")
# graph = g1.getGraph()
# nx.draw(graph,with_labels = True)
#-----------------------------------------------------------------------------#

