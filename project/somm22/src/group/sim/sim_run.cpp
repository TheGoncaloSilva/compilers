/*
 *  \author Alexandre Lameiro, Inês Santos, Mauro Filho
 */

#include "somm22.h"
#include "sim_module.h"

namespace somm22
{

    namespace group
    {

// ================================================================================== //

        /*
         * Solution may be based on a state machine with two states, which are related to the
         * type of events that are fetched from the Process Event Queue.
         * The meaningful cases are:
         * - ARRIVAL | TERMINATE
         * - POSTPONED
         */
        void simRun(uint32_t cnt)
        {
            soProbe(503, "%s(cnt: %u)\n", __func__, cnt);

			uint32_t i = 0;
			while(i < cnt || cnt == 0){
				//Fetch next process from current mask
				Event event = somm22::peqFetchNext(simGetCurrentSimMask());
				
				if (simGetCurrentSimMask() == 0b101){ // ARRIVAL | TERMINATE ------------------------
					//Mauro...
					sim::currentSimTime = event.eventTime;
					
					if (event.eventType == ARRIVAL){//ARRIVAL ------------------------
						if (somm22::pctGetProcessAddressSpaceSize(event.pid) <= somm22::memGetMaxAllowableSize()) {
							bool canAlloc = somm22::pctGetProcessAddressSpaceSize(event.pid) <= somm22::memGetBiggestHole();
							void * addr = NULL;
							if(canAlloc == false || somm22::peqGetFirstPostponedProcess() != 0){
								somm22::pctSetProcessState(event.pid, SWAPPED);
								somm22::peqInsert(POSTPONED, simGetCurrentSimTime(), event.pid);
							} else {
								addr = somm22::memAlloc(event.pid, somm22::pctGetProcessAddressSpaceSize(event.pid));
								somm22::pctSetProcessState(event.pid, RUNNING);
								somm22::pctSetProcessMemAddress(event.pid, addr);
								somm22::pctSetProcessStartTime(event.pid, simGetCurrentSimTime());
								somm22::peqInsert(TERMINATE, simGetCurrentSimTime() + somm22::pctGetProcessDuration(event.pid), event.pid);
							}
						} else {
							//DISCARDED pois não há memória o suficiente para alocar
							somm22::pctSetProcessState(event.pid, DISCARDED);
						}
					
					} else {//TERMINATE ------------------------
						//Alex...
						somm22::memFree(somm22::pctGetProcessMemAddress(event.pid));
						somm22::pctSetProcessState(event.pid, FINISHED);
						uint32_t p = somm22::peqGetFirstPostponedProcess();
						if(p != 0) {
							if(somm22::pctGetProcessAddressSpaceSize(p) <= somm22::memGetBiggestHole()) {
								sim::currentSimMask = 0b010;
							}
						}	
					}
				} else {//POSTPONED ------------------------
					//Ines...
                    bool canAlloc = somm22::pctGetProcessAddressSpaceSize(event.pid) <= somm22::memGetBiggestHole();
					void * addr = NULL;
					if(canAlloc == true){
						addr = somm22::memAlloc(event.pid, somm22::pctGetProcessAddressSpaceSize(event.pid));
                        somm22::pctSetProcessState(event.pid, RUNNING);
						somm22::pctSetProcessMemAddress(event.pid, addr);
						somm22::pctSetProcessStartTime(event.pid, simGetCurrentSimTime());
						somm22::peqInsert(TERMINATE, simGetCurrentSimTime() + somm22::pctGetProcessDuration(event.pid), event.pid);
                    }else{
                        //shouldn't happen
                        somm22::pctSetProcessState(event.pid, SWAPPED);
						somm22::peqInsert(POSTPONED, simGetCurrentSimTime(), event.pid);
                    }
                    uint32_t pid = somm22::peqGetFirstPostponedProcess();
                    if (pid != 0){
                        if (somm22::pctGetProcessAddressSpaceSize(pid) <= somm22::memGetBiggestHole()){
                            sim::currentSimMask = 0b010;
                        }else{
                            sim::currentSimMask = 0b101;
                        }
                    } else {
						sim::currentSimMask = 0b101;
					}
				}

                if (cnt != 0){ i++;}
                sim::currentSimStep++;
                if (somm22::peqIsEmpty()){ break;}
			}
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
